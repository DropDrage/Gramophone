package com.wholedetail.gramophone.ui.search.results

import com.wholedetail.gramophone.base.BaseViewModelFactory
import com.wholedetail.gramophone.network.repository.UserRepository
import javax.inject.Inject

class SearchResultsViewModelFactory @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModelFactory<SearchResultsViewModel>(SearchResultsViewModel::class.java) {

    override fun createViewModel() =
        SearchResultsViewModel(userRepository)

}