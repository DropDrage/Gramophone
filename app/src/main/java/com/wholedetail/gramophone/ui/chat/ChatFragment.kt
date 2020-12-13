package com.wholedetail.gramophone.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.data.model.Chat
import com.wholedetail.gramophone.databinding.FragmentChatBinding
import javax.inject.Inject

class ChatFragment(private val chat: Chat) : Fragment() {

    @Inject
    lateinit var chatViewModelFactory: ChatViewModelFactory

    private lateinit var viewModel: ChatViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        viewModel = ViewModelProvider(this, chatViewModelFactory).get(ChatViewModel::class.java).apply {
            chat = this@ChatFragment.chat
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        DataBindingUtil.inflate<FragmentChatBinding>(inflater, R.layout.fragment_chat, container, false).run {
            viewModel = this@ChatFragment.viewModel
            root
        }

}