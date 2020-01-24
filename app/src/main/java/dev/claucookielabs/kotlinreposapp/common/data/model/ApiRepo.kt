package dev.claucookielabs.kotlinreposapp.common.data.model

data class ApiRepo(
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val starsCount: Int
)
