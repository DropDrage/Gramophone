package com.wholedetail.gramophone.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sign_up_email.*

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backArrow?.setOnClickListener { activity!!.onBackPressed() }
    }
}
