package com.mcmouse88.note_app.feature_note.domain.util

sealed interface OrderType {
    data object Ascending : OrderType
    data object Descending : OrderType
}



