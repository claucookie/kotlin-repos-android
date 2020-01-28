package dev.claucookielabs.kotlinreposapp.common.data.repository

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiOwner
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Owner
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import retrofit2.HttpException
import java.io.IOException

class GithubDataRepository(private val remoteDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getReposByLanguage(languageName: String): ResultWrapper<List<Repo>> {
        return try {
            ResultWrapper.Success(remoteDataSource.getReposByLanguage(languageName).map { it.toDomain() })
        } catch (throwable: IOException) {
            ResultWrapper.NetworkError
        } catch (throwable: HttpException) {
            val code = throwable.code()
            val errorResponse = throwable.message()
            ResultWrapper.GenericError(code, errorResponse)
        }
    }

    override suspend fun getFileContent(
        ownerName: String,
        repoName: String,
        fileName: String
    ): ResultWrapper<String> {
        // TODO Code duplication in both functions, try to move to a generic function I can reuse.
        return try {
            ResultWrapper.Success(remoteDataSource.getFileContent(ownerName, repoName, fileName))
        } catch (throwable: IOException) {
            ResultWrapper.NetworkError
        } catch (throwable: HttpException) {
            val code = throwable.code()
            val errorResponse = throwable.message()
            ResultWrapper.GenericError(code, errorResponse)
        }

    }
}

private fun ApiRepo.toDomain(): Repo {
    return Repo(
        name = this.name,
        description = this.description,
        starsCount = this.stargazers_count.toString(),
        owner = this.owner.toDomain()
    )
}

private fun ApiOwner?.toDomain(): Owner {
    return Owner(
        userName = this?.login ?: "",
        avatarUrl = this?.avatar_url ?: PLACEHOLDER
    )
}

private const val PLACEHOLDER =
    "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2018/03/GitHub-brave-hed-796x418.jpg"
