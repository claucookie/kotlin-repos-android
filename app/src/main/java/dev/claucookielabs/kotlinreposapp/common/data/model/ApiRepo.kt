package dev.claucookielabs.kotlinreposapp.common.data.model

import com.squareup.moshi.Json

class ApiRepo(
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "owner") val apiOwner: ApiOwner?,
    @Json(name = "stargazers_count") val starsCount: Int
)

class ApiOwner(
    @Json(name = "login") val userName : String,
    @Json(name = "avatar_url") val avatarUrl: String
)
