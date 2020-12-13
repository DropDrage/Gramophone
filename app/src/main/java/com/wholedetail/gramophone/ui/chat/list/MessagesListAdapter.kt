package com.wholedetail.gramophone.ui.chat.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.data.BaseMessage
import com.wholedetail.gramophone.data.DateMessage
import com.wholedetail.gramophone.data.MessageType
import com.wholedetail.gramophone.data.model.Message
import com.wholedetail.gramophone.ui.chat.list.viewholder.DateMessageViewHolder
import com.wholedetail.gramophone.ui.chat.list.viewholder.MessageReceivedViewHolder
import com.wholedetail.gramophone.ui.chat.list.viewholder.MessageSendViewHolder
import com.wholedetail.gramophone.ui.chat.list.viewholder.MessageViewHolder

class MessagesListAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    private val messages = arrayListOf<BaseMessage>()


    init {
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()

                if (messages.isEmpty()) {
                    return
                }

                messages.sortBy { it.time }

                if (messages[0].type != MessageType.DATE) {
                    messages.add(0, DateMessage(messages[0].time))
                }
                if (messages.size > 2) {
                    var i = 2
                    while (i < messages.size) {
                        if (messages[i - 1].type != MessageType.DATE && messages[i].type != MessageType.DATE
                            && messages[i - 1].date.compareTo(messages[i].date) != 0
                        ) {
                            messages.add(i - 1, DateMessage(messages[i].time))
                            i++
                        }
                        i++
                    }
                }
            }
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (MessageType.values()[viewType]) {
            MessageType.SENT -> MessageSendViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_message_send, parent, false)
            )
            MessageType.RECEIVED -> MessageReceivedViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_message_received,
                    parent,
                    false
                )
            )
            MessageType.DATE -> DateMessageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_message_date, parent, false)
            )
        }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemViewType(position: Int) = messages[position].type.ordinal

    override fun getItemCount() = messages.size


    fun addMessage(message: Message) {
        messages.add(message)
        notifyDataSetChanged()
    }

    fun editMessage(message: Message) {
        messages[messages.indexOfFirst { it is Message && it.id == message.id }] = message
        notifyDataSetChanged()
    }

    fun removeMessage(message: Message) {
        messages.remove(message)
        notifyDataSetChanged()
    }

}