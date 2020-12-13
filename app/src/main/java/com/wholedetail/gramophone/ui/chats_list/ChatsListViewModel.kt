package com.wholedetail.gramophone.ui.chats_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wholedetail.gramophone.data.MessageType
import com.wholedetail.gramophone.data.model.Chat
import com.wholedetail.gramophone.data.model.Message
import com.wholedetail.gramophone.data.model.User
import org.joda.time.LocalDateTime

class ChatsListViewModel : ViewModel() {

    private val chats: MutableLiveData<List<Chat>> = MutableLiveData(listOf())

    fun updateChatsList() {
        chats.value = listOf(
            Chat(
                "Name",
                Message(
                    1L,
                    "LastMess",
                    User("FirstName", "last", "", true),
                    LocalDateTime.now(),
                    false,
                    MessageType.SENT
                ),
                10,
                false
            )
        )
    }

}