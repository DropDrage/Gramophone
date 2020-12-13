package com.wholedetail.gramophone.ui.chat

import androidx.databinding.ObservableField
import com.wholedetail.gramophone.base.BaseViewModel
import com.wholedetail.gramophone.data.model.Chat

class ChatViewModel : BaseViewModel() {

    lateinit var chat: Chat

    val message = ObservableField<String>()

    fun getChatAvatarUrl() = chat.lastMessage.sender.avatarUrl

}