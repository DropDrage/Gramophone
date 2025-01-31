package com.wholedetail.gramophone.account

import android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.wholedetail.gramophone.GramophoneApplication
import javax.inject.Inject

class AccountAuthenticatorService : Service() {

    @Inject
    lateinit var authenticator: AccountAuthenticator


    override fun onCreate() {
        GramophoneApplication.appComponent.inject(this)
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        return if (intent.action == ACTION_AUTHENTICATOR_INTENT) authenticator.iBinder else null
    }
}