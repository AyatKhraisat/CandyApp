package com.ayat.candyapp.utils

/**
 *Created by Ayat Khriasat on 19,May,2019 at 23:46
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
