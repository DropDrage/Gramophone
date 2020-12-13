package com.wholedetail.gramophone.network.service

import com.wholedetail.gramophone.data.model.Chat
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val CHATS_PAGE_SIZE = 20
private const val CHATS_ENDPOINT = "chats"

interface ChatService {

    @GET("$CHATS_ENDPOINT?size=$CHATS_PAGE_SIZE")
    fun getAllChats(@Query("page") pageNumber: Int): Call<List<Chat>>

    @POST(CHATS_ENDPOINT)
    fun addChat(@Body chat: Chat)

}