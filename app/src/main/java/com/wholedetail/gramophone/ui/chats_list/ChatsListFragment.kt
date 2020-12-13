package com.wholedetail.gramophone.ui.chats_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wholedetail.gramophone.R

class ChatsListFragment : Fragment() {

    private lateinit var viewModel: ChatsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ChatsListViewModel::class.java)
        //        val textView: TextView = root.findViewById(R.id.text_dashboard)
        /*messagesViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_chats_list, container, false)
    }
}