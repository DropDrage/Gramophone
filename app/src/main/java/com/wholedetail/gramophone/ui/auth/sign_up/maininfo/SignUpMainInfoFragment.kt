package com.wholedetail.gramophone.ui.auth.sign_up.maininfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.databinding.FragmentSignUpMainInfoBinding
import com.wholedetail.gramophone.utils.extensions.makeGone
import com.wholedetail.gramophone.utils.extensions.makeVisible
import kotlinx.android.synthetic.main.fragment_sign_up_main_info.*
import javax.inject.Inject

class SignUpMainInfoFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SignUpMainInfoViewModelFactory

    private lateinit var viewModel: SignUpMainInfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        val args by navArgs<SignUpMainInfoFragmentArgs>()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpMainInfoViewModel::class.java).apply {
            userEmail = args.email
            initFormValidObservable(this@SignUpMainInfoFragment)

            onSuccess = {
                loading.makeGone()
                findNavController().navigate(SignUpMainInfoFragmentDirections.actionMainInfoFragmentToSignUpCompleteFragment())
            }
            onError = {
                loading.makeGone()
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentSignUpMainInfoBinding>(
            inflater, R.layout.fragment_sign_up_main_info, container, false
        ).run {
            viewModel = this@SignUpMainInfoFragment.viewModel
            root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishSignUp.setOnClickListener {
            loading.makeVisible()
            viewModel.signUp()
        }
    }

}