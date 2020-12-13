package com.wholedetail.gramophone.ui.chat

import com.wholedetail.gramophone.base.BaseViewModelFactory
import javax.inject.Inject

class ChatViewModelFactory @Inject constructor() : BaseViewModelFactory<ChatViewModel>(ChatViewModel::class.java) {

    override fun createViewModel() =
        ChatViewModel()

}