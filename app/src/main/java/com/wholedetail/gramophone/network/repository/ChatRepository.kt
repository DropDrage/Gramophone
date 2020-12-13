package com.wholedetail.gramophone.network.repository

import com.wholedetail.gramophone.network.service.ChatService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(private val chatService: ChatService) {

}