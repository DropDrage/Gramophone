package com.wholedetail.gramophone

import android.app.Application
import com.wholedetail.gramophone.di.AppModule
import com.wholedetail.gramophone.di.ApplicationComponent
import com.wholedetail.gramophone.di.DaggerApplicationComponent

class GramophoneApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}