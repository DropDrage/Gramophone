package com.wholedetail.gramophone.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.databinding.FragmentLoginBinding
import com.wholedetail.gramophone.utils.extensions.makeGone
import com.wholedetail.gramophone.utils.extensions.makeVisible
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        viewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false).run {
            viewModel = this@LoginFragment.viewModel
            root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            initFormValidObservable(this@LoginFragment)

            loginFormState.observe(this@LoginFragment, {
                if (it.emailError != null) {
                    email.error = getString(it.emailError)
                }
                if (it.passwordError != null) {
                    password.error = getString(it.passwordError)
                }
            })
            loginResult.observe(this@LoginFragment, Observer {
                val loginResult = it ?: return@Observer

                loading.makeGone()
                if (loginResult.error != null) {
                    showLoginFailed(loginResult.error)
                } else if (loginResult.success != null) {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainActivity())
                    requireActivity().finish()
                }
            })
        }

        password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) viewModel.login()
            false
        }

        login.setOnClickListener {
            loading.makeVisible()
            viewModel.login()
        }

        signUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpEmailFragment())
        }
    }

    //ToDo
    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val PARAM_EMAIL = "email"
        const val PARAM_AUTH_TOKEN_TYPE = "param_auth_token_type"
    }

}