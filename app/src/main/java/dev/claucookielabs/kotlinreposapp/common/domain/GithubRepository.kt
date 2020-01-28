package dev.claucookielabs.kotlinreposapp.common.domain

import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper

interface GithubRepository {

    suspend fun getReposByLanguage(languageName: String): ResultWrapper<List<Repo>>
    suspend fun getFileContent(
        ownerName: String,
        repoName: String,
        fileName: String
    ): ResultWrapper<String>
}
