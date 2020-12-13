package com.wholedetail.gramophone.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.navArgs
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.ui.chat.ChatFragment
import com.wholedetail.gramophone.utils.extensions.addBackStackFragment

class ChatActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val args by navArgs<ChatActivityArgs>()

        addBackStackFragment(R.id.container, ChatFragment(args.chat))
    }

}