package com.wholedetail.gramophone.data.model

import com.wholedetail.gramophone.data.BaseMessage
import com.wholedetail.gramophone.data.MessageType
import org.joda.time.LocalDateTime

class Message(
    val id: Long,
    text: String,
    val sender: User,
    time: LocalDateTime,
    val isRead: Boolean,
    messageType: MessageType
) :
    BaseMessage(text, time, messageType)