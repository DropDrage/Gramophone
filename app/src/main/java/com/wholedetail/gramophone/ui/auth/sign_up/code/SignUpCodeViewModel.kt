package com.wholedetail.gramophone.ui.auth.sign_up.code

import com.wholedetail.gramophone.network.repository.AccountRepository
import com.wholedetail.gramophone.ui.auth.sign_up.SignUpViewModel

class SignUpCodeViewModel(private val accountRepository: AccountRepository) : SignUpViewModel() {

    lateinit var onResendCodeSuccess: () -> Unit


    fun verifyCode(code: String) {
        onSuccess()
        /*accountRepository.verifyCode(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it) {
                    onSuccess()
                } else {
                    throw IllegalArgumentException("Invalid code. Try resend")
                }
            }
            .doOnError {
                onError(it.message!!)
            }
            .onErrorResumeNext { Single.never() }
            .subscribe()
            .let(disposables::add)*/
    }

    fun resendCode(email: String) {
        onResendCodeSuccess()
        /*accountRepository.sendCode(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it) {
                    onResendCodeSuccess()
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