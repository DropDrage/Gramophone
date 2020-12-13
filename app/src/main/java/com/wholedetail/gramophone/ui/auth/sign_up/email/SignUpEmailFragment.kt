package com.wholedetail.gramophone.ui.auth.sign_up.email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.base.BaseFragment
import com.wholedetail.gramophone.databinding.FragmentSignUpEmailBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up_email.*
import kotlinx.android.synthetic.main.fragment_sign_up_email.email
import javax.inject.Inject

class SignUpEmailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: SignUpEmailViewModelFactory

    private lateinit var viewModel: SignUpEmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpEmailViewModel::class.java).apply {
            onSuccess = {
                findNavController().navigate(
                    SignUpEmailFragmentDirections.actionSignUpEmailFragmentToSignUpCodeFragment(viewModel.email.get()!!)
                )
            }
            onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        DataBindingUtil.inflate<FragmentSignUpEmailBinding>(inflater, R.layout.fragment_sign_up_email, container, false)
            .run {
                viewModel = this@SignUpEmailFragment.viewModel
                root
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) viewModel.sendCode()
            false
        }
        getCode.setOnClickListener { viewModel.sendCode() }
    }

}