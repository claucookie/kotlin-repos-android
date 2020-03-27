package dev.claucookielabs.kotlinreposapp.common.utils

import kotlin.coroutines.CoroutineContext

interface CoroutinesDispatcher {

    fun ui() : CoroutineContext
    fun io() : CoroutineContext

}
