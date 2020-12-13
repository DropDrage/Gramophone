package com.wholedetail.gramophone.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.account.AccountManagementService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var accountManagementService: AccountManagementService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GramophoneApplication.appComponent.inject(this)
        if (!accountManagementService.isUserLogged) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /* val appBarConfiguration = AppBarConfiguration(
             setOf(R.id.navigation_home, R.id.navigation_messages, R.id.navigation_search))*/
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        lifecycle.addObserver(WebSocketLifecycleWrapper("chat"))
    }

}