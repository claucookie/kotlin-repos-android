package dev.claucookielabs.kotlinreposapp.common.data.repository

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo

class GithubDataRepository(private val remoteDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getReposByLanguage(languageName: String): List<Repo> {
        return remoteDataSource.getReposByLanguage(languageName).map { it.toDomain() }
    }

}

private fun ApiRepo.toDomain(): Repo {
    return Repo(
        name = this.name,
        description = this.description,
        starsCount = this.starsCount.toString(),
        thumbnailUrl = this.apiOwner?.avatarUrl ?: PLACEHOLDER
    )
}

private const val PLACEHOLDER =
    "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2018/03/GitHub-brave-hed-796x418.jpg"
