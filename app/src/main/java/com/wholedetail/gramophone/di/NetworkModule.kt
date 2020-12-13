package com.wholedetail.gramophone.di

import com.wholedetail.gramophone.BuildConfig
import com.wholedetail.gramophone.network.repository.AccountRepository
import com.wholedetail.gramophone.network.repository.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val AUTH_MAPPING = "auth/"
private const val USERS_MAPPING = "users/"

@Module
class NetworkModule {

    @Provides
    fun provideAccountRepository(): AccountRepository = Retrofit.Builder()
        .baseUrl("${BuildConfig.serverUrl}/$AUTH_MAPPING")
        .client(OkHttpClient.Builder().build())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AccountRepository::class.java)

    @Provides
//    @Singleton
    fun provideAuthorizedRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.serverUrl)
        .client(OkHttpClient.Builder().build())//ToDo add auth headers
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    fun provideUserRepository(retrofit: Retrofit.Builder): UserRepository =
        retrofit.baseUrl("${BuildConfig.serverUrl}/$USERS_MAPPING")
            .build()
            .create(UserRepository::class.java)

}