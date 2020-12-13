package com.wholedetail.gramophone.ui.search.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wholedetail.gramophone.R
import com.wholedetail.gramophone.databinding.ItemSearchInstrumentsBinding
import com.wholedetail.gramophone.databinding.ItemSearchProfileBinding

class SearchFilterPagerAdapter(private val viewModel: SearchFilterViewModel) :
    RecyclerView.Adapter<SearchFilterPage>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> SearchFilterPage(
            DataBindingUtil.inflate<ItemSearchInstrumentsBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_search_instruments,
                parent,
                false
            ).run {
                viewModel = this@SearchFilterPagerAdapter.viewModel
                root
            }
        )
        1 -> SearchFilterPage(
            DataBindingUtil.inflate<ItemSearchProfileBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_search_profile,
                parent,
                false
            ).run {
                viewModel = this@SearchFilterPagerAdapter.viewModel
                root
            })
        else -> SearchFilterPage(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_instruments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchFilterPage, position: Int) {
//        holder
    }

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int) = position
}

class SearchFilterPage(view: View) : RecyclerView.ViewHolder(view)