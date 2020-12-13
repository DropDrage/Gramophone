package com.wholedetail.gramophone.ui.search.results

import androidx.lifecycle.MutableLiveData
import com.wholedetail.gramophone.base.BaseViewModel
import com.wholedetail.gramophone.data.model.SearchData
import com.wholedetail.gramophone.data.model.User
import com.wholedetail.gramophone.network.repository.UserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchResultsViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    val name = MutableLiveData<String>()


    lateinit var searchData: SearchData

    lateinit var onSearch: (List<User>) -> Unit


    fun search() {
        userRepository.searchUsers(searchData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                onSearch(it)
            }
            .doOnError {
                it.printStackTrace()
            }
            .onErrorResumeNext { Single.never() }
            .subscribe()
            .let(disposables::add)
    }

}