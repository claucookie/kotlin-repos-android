package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo

class GithubRemoteDataSource(
    private val githubService: GithubService,
    private val githubContentService: GithubContentService
) : GithubDataSource {

    override suspend fun getReposByLanguage(languageName: String): List<ApiRepo> {
        val response = githubService.fetchRepositories(
            query = "language:$languageName",
            sort = "stars",
            order = "desc"
        )
        return response.body()?.items ?: emptyList()
    }

    override suspend fun getFileContent(
        ownerName: String,
        repoName: String,
        fileName: String
    ): String {
        val filesResult = githubService.fetchRepositoryContents(ownerName, repoName)
        val fileFound = filesResult.body()
            ?.firstOrNull { file ->
                file.name.toLowerCase() == fileName.toLowerCase()
            }
        return githubContentService.fetchRepositoryFileContent(
            ownerName,
            repoName,
            fileFound?.name ?: fileName
        ).body() ?: ""
    }
}
