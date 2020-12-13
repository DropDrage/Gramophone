package com.wholedetail.gramophone.data.model

import androidx.annotation.StringRes
import com.wholedetail.gramophone.R

data class Instrument(
    val id: Long,
    val skillLevel: Int = 0,
    val instrumentType: InstrumentType,
    val genres: Set<MusicGenre>
)


enum class InstrumentType(@StringRes override val entityNameResId: Int) : Named {
    GUITAR(R.string.instrument_type_guitar),
    PIANO(R.string.instrument_type_piano),
    VOCAL(R.string.instrument_type_vocal),
    BASS(R.string.instrument_type_bass),
    CELLO(R.string.instrument_type_cello),
    DRUMS(R.string.instrument_type_drums),
    FLUTE(R.string.instrument_type_flute),
    SAXOPHONE(R.string.instrument_type_saxophone)
}

enum class MusicGenre(@StringRes override val entityNameResId: Int) : Named {
    ROCK(R.string.music_genre_rock),
    BLUES(R.string.music_genre_blues),
    JAZZ(R.string.music_genre_blues),
    METAL(R.string.music_genre_jazz),
    CLASSICAL(R.string.music_genre_metal),
    RAP(R.string.music_genre_classical),
    FOLK(R.string.music_genre_rap)
}