package com.wholedetail.gramophone.ui.search.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.databinding.FragmentSearchResultsBinding
import javax.inject.Inject

class SearchResultsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SearchResultsViewModelFactory

    private lateinit var viewModel: SearchResultsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args by navArgs<SearchResultsFragmentArgs>()

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchResultsViewModel::class.java).apply {
            searchData = args.searchData


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentSearchResultsBinding>(
            inflater,
            R.layout.fragment_search_results_,
            container,
            false
        ).run {

            root
        }
}