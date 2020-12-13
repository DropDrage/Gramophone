package com.wholedetail.gramophone.ui.chat.list.viewholder

import com.wholedetail.gramophone.data.BaseMessage
import com.wholedetail.gramophone.databinding.ItemMessageReceivedBinding

class MessageReceivedViewHolder(private val binding: ItemMessageReceivedBinding) : BindableMessageViewHolder(binding) {

    override fun bind(message: BaseMessage) {
        binding.itemMessage = message
    }

}