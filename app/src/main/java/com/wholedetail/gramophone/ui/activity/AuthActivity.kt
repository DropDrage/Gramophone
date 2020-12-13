package com.wholedetail.gramophone.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.wholedetail.gramophone.R

class AuthActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)

        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.auth_navigation)
    }
}
