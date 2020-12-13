package com.wholedetail.gramophone.ui.auth.sign_up.maininfo

import com.wholedetail.gramophone.account.AccountManagementService
import com.wholedetail.gramophone.base.BaseViewModelFactory
import com.wholedetail.gramophone.network.repository.AccountRepository
import javax.inject.Inject

class SignUpMainInfoViewModelFactory @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountManagementService: AccountManagementService
) : BaseViewModelFactory<SignUpMainInfoViewModel>(SignUpMainInfoViewModel::class.java) {

    override fun createViewModel() =
        SignUpMainInfoViewModel(accountRepository, accountManagementService)
}