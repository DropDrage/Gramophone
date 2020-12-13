package com.wholedetail.gramophone.ui.chats_list.list

import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.gramophone.data.model.Chat
import com.wholedetail.gramophone.databinding.ItemChatListBinding

class ChatsListViewHolder(private val binding: ItemChatListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chat: Chat) {
        binding.listItem = chat
    }

}