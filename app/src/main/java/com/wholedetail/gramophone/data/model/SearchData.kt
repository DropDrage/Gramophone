package com.wholedetail.gramophone.data.model

import java.io.Serializable

data class SearchData(
    val firstName: String? = null,
    val lastName: String? = null,
    val instrumentType: InstrumentType? = null,
    val skillLevel: Int = 0,
    val instrumentGenres: Set<MusicGenre>? = null
) : Serializable