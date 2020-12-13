package com.wholedetail.gramophone.ui.search.filter

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.wholedetail.gramophone.data.model.InstrumentType
import com.wholedetail.gramophone.data.model.MusicGenre
import com.wholedetail.gramophone.data.model.SearchData

class SearchFilterViewModel(
    private val context: Context
) : ViewModel() {

    val instrumentType = ObservableField<InstrumentType>()
    val instrumentSkill = ObservableField<Float>()
    val genre = ObservableField<MusicGenre>()

    val country = ObservableField<String>()
    val city = ObservableField<String>()
    val metro = ObservableField<String>()


    val instruments = InstrumentType.values().map { context.getString(it.entityNameResId) }
    val genres = MusicGenre.values().map { context.getString(it.entityNameResId) }


    fun convertToSearchData() =
        SearchData(
            instrumentType = instrumentType.get(),
            skillLevel = instrumentSkill.get()?.toInt() ?: 0
        )

}