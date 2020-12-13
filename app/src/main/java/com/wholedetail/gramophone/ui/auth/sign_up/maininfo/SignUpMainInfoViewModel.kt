package com.wholedetail.gramophone.ui.auth.sign_up.maininfo

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.wholedetail.gramophone.account.AccountManagementService
import com.wholedetail.gramophone.account.credentials.UserCredentials
import com.wholedetail.gramophone.data.ObservableFormValid
import com.wholedetail.gramophone.network.repository.AccountRepository
import com.wholedetail.gramophone.ui.auth.sign_up.SignUpViewModel
import com.wholedetail.gramophone.utils.extensions.isPasswordValid

class SignUpMainInfoViewModel(
    private val accountRepository: AccountRepository,
    private val accountManagementService: AccountManagementService
) : SignUpViewModel() {

    lateinit var userEmail: String

    val userFirstName = MutableLiveData("")
    val userLastName = MutableLiveData("")
    val userPassword = MutableLiveData("")

    lateinit var isFormValid: ObservableFormValid


    fun initFormValidObservable(lifecycleOwner: LifecycleOwner) {
        isFormValid = ObservableFormValid(lifecycleOwner,
            userFirstName to { (it as String).isNotBlank() },
            userLastName to { (it as String).isNotBlank() },
            userPassword to { (it as String).isPasswordValid() })
    }

    fun signUp() {
        accountManagementService.addAccount(UserCredentials(userEmail, userPassword.value!!), "token")
        onSuccess()
        /*accountRepository.signUp(
            UserRegistrationData(userEmail, userFirstName.value!!, userLastName.value!!, userPassword.value!!)
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                accountManagementService.addAccount(UserCredentials(userEmail, userPassword.value!!), it)
                onSuccess()
            }
            .doOnError {
                onError(it.message!!)
            }
            .onErrorResumeNext { Single.never() }
            .subscribe()
            .let(disposables::add)*/
    }

}