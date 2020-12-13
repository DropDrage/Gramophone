package com.wholedetail.gramophone.ui.search.filter

import android.content.Context
import com.wholedetail.gramophone.base.BaseViewModelFactory
import javax.inject.Inject

class SearchFilterViewModelFactory @Inject constructor(
    private val context: Context
) : BaseViewModelFactory<SearchFilterViewModel>(SearchFilterViewModel::class.java) {

    override fun createViewModel(): SearchFilterViewModel =
        SearchFilterViewModel(context)

}