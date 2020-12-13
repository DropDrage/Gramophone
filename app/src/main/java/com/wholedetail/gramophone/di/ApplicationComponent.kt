package com.wholedetail.gramophone.di

import com.wholedetail.gramophone.account.AccountAuthenticator
import com.wholedetail.gramophone.account.AccountAuthenticatorService
import com.wholedetail.gramophone.ui.activity.MainActivity
import com.wholedetail.gramophone.ui.auth.login.LoginFragment
import com.wholedetail.gramophone.ui.auth.sign_up.code.SignUpCodeFragment
import com.wholedetail.gramophone.ui.auth.sign_up.email.SignUpEmailFragment
import com.wholedetail.gramophone.ui.auth.sign_up.maininfo.SignUpMainInfoFragment
import com.wholedetail.gramophone.ui.chat.ChatFragment
import com.wholedetail.gramophone.ui.search.filter.SearchFilterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(accountAuthenticator: AccountAuthenticator)
    fun inject(accountAuthenticatorService: AccountAuthenticatorService)

    fun inject(mainActivity: MainActivity)

    fun inject(chatFragment: ChatFragment)
    fun inject(chatFragment: LoginFragment)
    fun inject(signUpEmailFragment: SignUpEmailFragment)
    fun inject(signUpCodeFragment: SignUpCodeFragment)
    fun inject(mainInfoFragment: SignUpMainInfoFragment)
    fun inject(searchFilterFragment: SearchFilterFragment)

}