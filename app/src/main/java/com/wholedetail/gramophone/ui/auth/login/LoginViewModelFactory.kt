package com.wholedetail.gramophone.ui.auth.login

import com.wholedetail.gramophone.account.AccountManagementService
import com.wholedetail.gramophone.base.BaseViewModelFactory
import com.wholedetail.gramophone.network.service.LoginService
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(private val accountManagementService: AccountManagementService) :
    BaseViewModelFactory<LoginViewModel>(LoginViewModel::class.java) {

    override fun createViewModel(): LoginViewModel =
        LoginViewModel(LoginService(), accountManagementService)
}