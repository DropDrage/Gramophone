package com.wholedetail.gramophone.network.repository

import com.wholedetail.gramophone.account.credentials.UserCredentials
import com.wholedetail.gramophone.ui.activity.data.UserRegistrationData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AccountRepository {

    @GET("send-code")
    fun sendCode(@Query("email") email: String): Single<Boolean>

    @GET("send-code")
    fun verifyCode(@Query("code") code: String): Single<Boolean>

    @POST("registration")
    fun signUp(@Body user: UserRegistrationData): Single<String>

    /**
     * @return access token
     */
    @POST("login")
    fun login(@Body user: UserCredentials): Single<String>

    @POST("logout")
    fun logout(): Single<Boolean>

}