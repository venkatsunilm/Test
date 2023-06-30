package com.app.youtubevideos.utitlies

open class SingleEvent<out T,D>(private val content: T, private val type:D) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): Pair<T?,D?> {
        return if (hasBeenHandled) {
            Pair(null,null)
        } else {
            hasBeenHandled = true
            Pair(content,type)
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}