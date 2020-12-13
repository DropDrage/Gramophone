package com.wholedetail.gramophone.di

import android.app.Application
import android.content.Context
import com.wholedetail.gramophone.account.AccountAuthenticator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context = app

    @Provides
    @Singleton
    fun provideAccountAuthenticator(context: Context) = AccountAuthenticator(context)

}