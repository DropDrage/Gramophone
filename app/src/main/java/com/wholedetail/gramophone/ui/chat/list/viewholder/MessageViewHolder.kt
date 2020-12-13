package com.wholedetail.gramophone.ui.chat.list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.gramophone.data.BaseMessage

abstract class MessageViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    abstract fun bind(message: BaseMessage)
}