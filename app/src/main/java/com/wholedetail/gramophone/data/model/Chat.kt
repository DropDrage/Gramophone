package com.wholedetail.gramophone.data.model

import com.wholedetail.gramophone.utils.extensions.formatFullDateDot
import com.wholedetail.gramophone.utils.extensions.formatHoursMinutes
import org.joda.time.Duration
import org.joda.time.LocalDateTime
import java.io.Serializable


private val countPostfixes = mapOf(Pair(1_000, "K"), Pair(1_000_000, "M"))

private const val DATE_TIME_FORMAT = "dd MMM, HH:mm"

class Chat(val chatName: String, val lastMessage: Message, unreadCount: Long, val isNotificationEnabled: Boolean) :
    Serializable {

    val unreadCountFormatted: String
    val timeFormatted: String

    init {
        val postfix = countPostfixes.entries.find { entry -> unreadCount / entry.key > 0 }
        unreadCountFormatted =
            if (postfix != null) "${unreadCount / postfix.key}${postfix.value}"
            else unreadCount.toString()

        timeFormatted = formatLastMessageTime()
    }

    private fun formatLastMessageTime(): String {
        val nowDateTime = LocalDateTime.now()
        val lastMessageDateTime = LocalDateTime(lastMessage.time)
        val timeAgo = Duration(lastMessageDateTime.toDateTime().millis, nowDateTime.toDateTime().millis)

        return when {
            nowDateTime.year != lastMessageDateTime.year -> lastMessageDateTime.formatFullDateDot()
            timeAgo.standardDays == 0L && timeAgo.standardHours > 6 -> lastMessageDateTime.formatHoursMinutes()
            timeAgo.standardDays == 0L && timeAgo.standardHours <= 6 -> "${timeAgo.standardHours}Ч"
            timeAgo.standardHours == 0L && timeAgo.standardMinutes > 0 -> "${timeAgo.standardMinutes}М"
            timeAgo.standardMinutes == 0L -> "${timeAgo.standardMinutes}С"
            else -> lastMessageDateTime.toString(DATE_TIME_FORMAT)
        }
    }

}