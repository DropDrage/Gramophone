package com.wholedetail.gramophone.ui.chat.list.viewholder

import com.wholedetail.gramophone.data.BaseMessage
import com.wholedetail.gramophone.data.model.Message
import com.wholedetail.gramophone.databinding.ItemMessageSendBinding

class MessageSendViewHolder(private val binding: ItemMessageSendBinding) : BindableMessageViewHolder(binding) {

    override fun bind(message: BaseMessage) {
        binding.itemMessage = message as Message
    }

}