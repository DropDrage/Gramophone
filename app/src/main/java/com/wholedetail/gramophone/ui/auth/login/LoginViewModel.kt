package com.wholedetail.gramophone.ui.auth.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.account.AccountManagementService
import com.wholedetail.gramophone.account.credentials.UserCredentials
import com.wholedetail.gramophone.base.BaseViewModel
import com.wholedetail.gramophone.data.ObservableFormValid
import com.wholedetail.gramophone.network.service.LoginService
import com.wholedetail.gramophone.ui.activity.ui.login.LoginFormState
import com.wholedetail.gramophone.ui.activity.ui.login.LoginResult
import com.wholedetail.gramophone.utils.extensions.isEmailValid
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(
    private val loginService: LoginService,
    private val accountManagementService: AccountManagementService
) : BaseViewModel() {

    val userEmail = MutableLiveData("")
    val userPassword = MutableLiveData("")

    private val _loginForm = MutableLiveData(LoginFormState())
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    lateinit var isDataValid: ObservableFormValid


    fun initFormValidObservable(lifecycleOwner: LifecycleOwner) {
        isDataValid = ObservableFormValid(lifecycleOwner,
            userEmail to { validateEmailChanged(it as String) },
            userPassword to { (it as String).isNotBlank() })
    }

    fun login() {
        val credentials = UserCredentials(userEmail.value!!, userPassword.value!!)
        loginService.login(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _loginResult.value = LoginResult(it)
                accountManagementService.addAccount(credentials, it)
            }
            .doOnError {
                _loginResult.value = LoginResult(error = R.string.login_failed)
                it.printStackTrace()
            }
            .onErrorResumeNext { Single.never() }
            .subscribe()
            .let(disposables::add)
    }

    private fun validateEmailChanged(email: String) = when {
        !email.isEmailValid() -> {
            _loginForm.value = LoginFormState(emailError = R.string.validation_email)
            false
        }
        email.isBlank() -> false
        else -> true
    }

}