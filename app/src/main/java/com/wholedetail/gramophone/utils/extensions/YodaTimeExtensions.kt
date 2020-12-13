package com.wholedetail.gramophone.utils.extensions

import org.joda.time.LocalDateTime

private const val HOURS_MINUTES_FORMAT = "HH:mm"
private const val FULL_DATE_DOT_FORMAT = "dd.MM.yyyy"

fun LocalDateTime.formatHoursMinutes(): String =
    toString(HOURS_MINUTES_FORMAT)

fun LocalDateTime.formatFullDateDot(): String =
    toString(FULL_DATE_DOT_FORMAT)

