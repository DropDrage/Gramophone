package com.wholedetail.gramophone.ui.chats_list

import com.wholedetail.gramophone.base.BaseViewModelFactory
import javax.inject.Inject

class ChatsListViewModelFactory @Inject constructor(

) : BaseViewModelFactory<ChatsListViewModel>(ChatsListViewModel::class.java) {

    override fun createViewModel(): ChatsListViewModel =
        ChatsListViewModel()

}