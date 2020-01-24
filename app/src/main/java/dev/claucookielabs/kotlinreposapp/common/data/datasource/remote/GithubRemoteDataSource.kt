package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo

class GithubRemoteDataSource(private val githubService: GithubService) : GithubDataSource {

    override suspend fun getReposByLanguage(languageName: String): List<ApiRepo> {
        val response = githubService.fetchRepositories(
            query = "language:$languageName",
            sort = "stars",
            order = "desc"
        )
        return response.body()?.items ?: emptyList()
    }
}
