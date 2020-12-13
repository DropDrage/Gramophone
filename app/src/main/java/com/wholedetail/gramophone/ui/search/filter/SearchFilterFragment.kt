package com.wholedetail.gramophone.ui.search.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.wholedetail.gramophone.GramophoneApplication
import com.wholedetail.gramophone.R
import kotlinx.android.synthetic.main.fragment_search_filter.*
import javax.inject.Inject

class SearchFilterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SearchFilterViewModelFactory

    private lateinit var viewModel: SearchFilterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GramophoneApplication.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchFilterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_search_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = SearchFilterPagerAdapter(viewModel)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.search_instruments)
                1 -> getString(R.string.search_profile)
                else -> ""
            }
        }.attach()
    }

}