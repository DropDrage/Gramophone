package com.wholedetail.gramophone.ui.auth.sign_up.code

import com.wholedetail.gramophone.base.BaseViewModelFactory
import com.wholedetail.gramophone.network.repository.AccountRepository
import javax.inject.Inject

class SignUpCodeViewModelFactory @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModelFactory<SignUpCodeViewModel>(SignUpCodeViewModel::class.java) {

    override fun createViewModel() = SignUpCodeViewModel(accountRepository)

}