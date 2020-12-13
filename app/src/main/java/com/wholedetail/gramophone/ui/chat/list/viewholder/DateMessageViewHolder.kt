package com.wholedetail.gramophone.ui.chat.list.viewholder

import android.view.View
import com.wholedetail.gramophone.data.BaseMessage
import kotlinx.android.synthetic.main.item_message_date.view.*

class DateMessageViewHolder(private val root: View) : MessageViewHolder(root) {

    override fun bind(message: BaseMessage) {
        root.date.text = message.text
    }

}