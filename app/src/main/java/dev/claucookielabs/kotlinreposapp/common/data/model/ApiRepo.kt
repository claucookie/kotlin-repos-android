package dev.claucookielabs.kotlinreposapp.common.data.model

data class ApiRepo(
    var name: String,
    var description: String,
    var owner: ApiOwner,
    var stargazers_count: Int
)

data class ApiOwner(
    var login: String,
    var avatar_url: String
)
