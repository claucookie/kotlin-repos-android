package dev.claucookielabs.kotlinreposapp.common.data.datasource

import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo

interface GithubDataSource {

    suspend fun getReposByLanguage(languageName: String) : List<ApiRepo>
    suspend fun getFileContent(
        ownerName: String,
        repoName: String,
        fileName: String
    ): String
}
