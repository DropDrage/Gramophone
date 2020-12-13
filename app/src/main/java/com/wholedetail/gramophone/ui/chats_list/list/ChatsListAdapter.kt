package com.wholedetail.gramophone.ui.chats_list.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.data.model.Chat

class ChatsListAdapter : RecyclerView.Adapter<ChatsListViewHolder>() {

    private val chats = arrayListOf<Chat>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsListViewHolder =
        ChatsListViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_chat_list, parent, false)
        )

    override fun onBindViewHolder(holder: ChatsListViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount() = chats.size

}