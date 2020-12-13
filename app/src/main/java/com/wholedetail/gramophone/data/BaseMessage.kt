package com.wholedetail.gramophone.data

import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

abstract class BaseMessage(val text: String, val time: LocalDateTime, val type: MessageType) {

    val date: LocalDate = time.toLocalDate()

}