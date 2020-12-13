package com.wholedetail.gramophone.network.repository

import com.wholedetail.gramophone.data.model.SearchData
import com.wholedetail.gramophone.data.model.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRepository {

    @GET("{id}")
    fun getUser(@Path("id") id: Long): Single<User>

    @GET
    fun searchUsers(@Body searchData: SearchData): Single<List<User>>

}