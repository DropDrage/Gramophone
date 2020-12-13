package com.wholedetail.gramophone.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.ui.activity.AuthActivity
import com.wholedetail.gramophone.ui.auth.login.LoginFragment

class AccountAuthenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

    init {
        GramophoneApplication.appComponent.inject(this)
    }


    override fun addAccount(
        response: AccountAuthenticatorResponse,
        accountType: String,
        authTokenType: String,
        requiredFeatures: Array<out String>,
        options: Bundle
    ): Bundle {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
            putExtra(LoginFragment.PARAM_AUTH_TOKEN_TYPE, authTokenType)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        }
        return Bundle().apply { putParcelable(AccountManager.KEY_INTENT, intent) }
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account,
        authTokenType: String,
        options: Bundle
    ): Bundle {
        val bundle = Bundle()

        val accountManager = AccountManager.get(context)
        val authToken = accountManager.peekAuthToken(account, authTokenType)

        if (!authToken.isNullOrEmpty()) {
            return bundle.apply {
                putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
                putString(AccountManager.KEY_ACCOUNT_TYPE, USER_ACCOUNT_TYPE)
                putString(AccountManager.KEY_AUTHTOKEN, authToken)
            }
        }

        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra(LoginFragment.PARAM_EMAIL, account.name)
            putExtra(LoginFragment.PARAM_AUTH_TOKEN_TYPE, authTokenType)
            putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        }

        return bundle.apply { putParcelable(AccountManager.KEY_INTENT, intent) }
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ) = Bundle().apply {
        putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false)
    }

    override fun getAuthTokenLabel(authTokenType: String?) =
        if (authTokenType == AUTH_TOKEN_TYPE) authTokenType else null


    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ) = null

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ) = null

    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?) = null

}
