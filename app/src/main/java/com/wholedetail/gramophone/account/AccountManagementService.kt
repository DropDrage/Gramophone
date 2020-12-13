package com.wholedetail.gramophone.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import com.wholedetail.gramophone.account.credentials.UserCredentials
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject
import javax.inject.Singleton

const val USER_ACCOUNT_TYPE = "user"
const val AUTH_TOKEN_TYPE = "auth_token"

@Singleton
class AccountManagementService @Inject constructor(context: Context) {

    private val accountManager = AccountManager.get(context)
    private val account get() = accountManager.accounts.firstOrNull { it.type == USER_ACCOUNT_TYPE }

    private val userLoggedStatusChanged = PublishSubject.create<Unit>()

    private val accessTokenSubject = BehaviorSubject.create<String>()
    val accessTokenObservable: Observable<String> get() = accessTokenSubject

    private val isUserLoggedSubject: Subject<Boolean> = BehaviorSubject.create<Boolean>().apply { onNext(isUserLogged) }
    val isUserLogged: Boolean get() = accountManager.getAccountsByType(USER_ACCOUNT_TYPE).isNotEmpty()

    val authToken get() = account?.run { accountManager.blockingGetAuthToken(this, AUTH_TOKEN_TYPE, false) }


    fun getObservableAccessTokenFromAccount(): Observable<String> =
        Observable.create<String> {
            it.onNext(
                account?.run {
                    accountManager.blockingGetAuthToken(this, AUTH_TOKEN_TYPE, false)
                } ?: "")
            return@create
        }.subscribeOn(Schedulers.io())

    fun updateAccessAndPassword(accessToken: String, password: String) {
        account?.let {
            accountManager.setAuthToken(it, AUTH_TOKEN_TYPE, accessToken)
            accountManager.setPassword(it, password)
            accessTokenSubject.onNext(accessToken)
        }
    }

    fun isUserLoggedObservable(): Observable<Boolean> = isUserLoggedSubject

    fun getUserLoggedStatusChange(): Subject<Unit> = userLoggedStatusChanged

    fun addAccount(credentials: UserCredentials, authToken: String) {
        val account = Account(credentials.email, USER_ACCOUNT_TYPE)
        accountManager.getAccountsByType(USER_ACCOUNT_TYPE).forEach()
        {
            it.run {
                if (account.name == this.name) {
                    accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, authToken)
                    accountManager.setPassword(account, credentials.password)
                    return
                } else {
                    removeAccount(it)
                }
            }
        }
        if (accountManager.addAccountExplicitly(account, credentials.password, null)) {
            accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, authToken)
        }
        setLoggedStatus(true)
    }

    fun removeAccount() {
        setLoggedStatus(false)
        accountManager.getAccountsByType(USER_ACCOUNT_TYPE).forEach { removeAccount(it) }
        accessTokenSubject.onNext("")
    }

    fun setLoggedStatus(status: Boolean) {
        isUserLoggedSubject.onNext(status)
        userLoggedStatusChanged.onNext(Unit)
    }

    @Suppress("DEPRECATION")
    private fun removeAccount(account: Account) {
        accountManager.removeAccount(account, null, null)
    }
}