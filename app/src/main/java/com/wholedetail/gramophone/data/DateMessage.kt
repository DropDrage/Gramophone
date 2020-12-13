package com.wholedetail.gramophone.data

import com.wholedetail.gramophone.utils.extensions.formatFullDateDot
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

private const val DATE_MONTH_FORMAT = "dd MMM"

class DateMessage(time: LocalDateTime) :
    BaseMessage(time.format(), time.toLocalDate().toDateTimeAtStartOfDay().toLocalDateTime(), MessageType.DATE)

private fun LocalDateTime.format() = when (year) {
    LocalDate.now().year -> toString(DATE_MONTH_FORMAT)
    else -> formatFullDateDot()
}