package dev.claucookielabs.kotlinreposapp.common.data

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo

class GithubRemoteDataSource : GithubDataSource {

    override fun getReposByLanguage(languageName: String): List<ApiRepo> {
        return listOf(
            ApiRepo(
                name = "Android",
                description = "This is a short description",
                thumbnailUrl = "https://avatars3.githubusercontent.com/u/32689599?v=4",
                starsCount = 100
            )
        )
    }
}
