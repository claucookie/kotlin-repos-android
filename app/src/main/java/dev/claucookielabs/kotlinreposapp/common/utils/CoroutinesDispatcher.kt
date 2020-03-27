package dev.claucookielabs.kotlinreposapp.common.utils

import kotlin.coroutines.CoroutineContext

interface CoroutinesDispatcher {

    fun uiDispatcher() : CoroutineContext
    fun ioDispatcher() : CoroutineContext

}
