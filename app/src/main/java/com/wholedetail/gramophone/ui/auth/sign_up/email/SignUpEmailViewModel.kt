package com.wholedetail.gramophone.ui.auth.sign_up.email

import androidx.databinding.ObservableField
import com.wholedetail.gramophone.network.repository.AccountRepository
import com.wholedetail.gramophone.ui.auth.sign_up.SignUpViewModel

class SignUpEmailViewModel(private val accountRepository: AccountRepository) : SignUpViewModel() {

    val email = ObservableField("")

    fun sendCode() {
        onSuccess()
        /*accountRepository.sendCode(email.get()!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it) {
                    onSuccess()
                } else {
                    throw IllegalArgumentException("Invalid email. Mail not sent")
                }
            }
            .doOnError {
                onError(it.message!!)
            }
            .onErrorResumeNext { Single.never() }
            .subscribe()
            .let(disposables::add)*/
    }

}