package com.wholedetail.gramophone.ui.auth.sign_up.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wholedetail.gramophone.R
import kotlinx.android.synthetic.main.fragment_sign_up_complete.*

class SignUpCompleteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_sign_up_complete, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile.setOnClickListener {
            //ToDo editProfile onClick
        }
        notNow.setOnClickListener {
            findNavController().navigate(SignUpCompleteFragmentDirections.actionSignUpCompleteFragmentToMainActivity())
            activity!!.finish()
        }
    }
}