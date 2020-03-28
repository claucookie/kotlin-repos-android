package dev.claucookielabs.kotlinreposapp

import dev.claucookielabs.kotlinreposapp.common.data.model.ApiFile
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiOwner
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo
import dev.claucookielabs.kotlinreposapp.common.domain.model.Owner
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo

fun aListOfApiRepos(): List<ApiRepo> {
    return listOf(
        ApiRepo(
            name = "Kotlin repo name",
            description = "Kotlin repo description",
            owner = ApiOwner(
                login = "username",
                avatar_url = "url"
            ),
            stargazers_count = 1000
        )
    )
}

fun aListOfRepos(): List<Repo> {
    return listOf(
        Repo(
            name = "Kotlin repo name",
            description = "Kotlin repo description",
            owner = Owner(
                userName = "username",
                avatarUrl = "url"
            ),
            starsCount = "1000"
        )
    )
}

fun aListOfApiFiles(): List<ApiFile> {
    return listOf(
        ApiFile(
            name = "gitignore",
            download_url = "url"
        ),
        ApiFile(
            name = "Readme.md",
            download_url = "url"
        )
    )
}