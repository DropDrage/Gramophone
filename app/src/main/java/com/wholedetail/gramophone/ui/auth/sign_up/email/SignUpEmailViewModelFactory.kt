package com.wholedetail.gramophone.ui.auth.sign_up.email

import com.wholedetail.gramophone.base.BaseViewModelFactory
import com.wholedetail.gramophone.network.repository.AccountRepository
import javax.inject.Inject

class SignUpEmailViewModelFactory @Inject constructor(private val accountRepository: AccountRepository) :
    BaseViewModelFactory<SignUpEmailViewModel>(SignUpEmailViewModel::class.java) {

    override fun createViewModel() =
        SignUpEmailViewModel(accountRepository)
}