package com.mcmouse88.custom_pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}