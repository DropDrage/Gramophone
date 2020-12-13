package com.wholedetail.gramophone.ui.auth.sign_up.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up_code.*
import javax.inject.Inject

class SignUpCodeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: SignUpCodeViewModelFactory

    private lateinit var viewModel: SignUpCodeViewModel

    private val args by navArgs<SignUpCodeFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpCodeViewModel::class.java).apply {
            onSuccess = {
                findNavController().navigate(
                    SignUpCodeFragmentDirections.actionSignUpCodeFragmentToMainInfoFragment(args.email)
                )
            }
            onResendCodeSuccess = { resendCode.isEnabled = true }
            onError = {
                resendCode.isEnabled = true
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_sign_up_code, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otpEditText.setOnEnterCompleteListener { viewModel.verifyCode(it) }
        resendCode.setOnClickListener {
            otpEditText.setText("")
            resendCode.isEnabled = false
            viewModel.resendCode(args.email)
        }
    }

}