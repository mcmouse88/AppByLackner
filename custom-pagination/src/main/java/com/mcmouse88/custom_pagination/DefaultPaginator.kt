package com.mcmouse88.custom_pagination

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey: Key = initialKey
    private var isMakingRequest: Boolean = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated.invoke(true)
        val result = onRequest.invoke(currentKey)
        isMakingRequest = false
        val items = result.getOrElse {
            onError.invoke(it)
            onLoadUpdated.invoke(false)
            return
        }

        currentKey = getNextKey.invoke(items)
        onSuccess.invoke(items, currentKey)
        onLoadUpdated.invoke(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}