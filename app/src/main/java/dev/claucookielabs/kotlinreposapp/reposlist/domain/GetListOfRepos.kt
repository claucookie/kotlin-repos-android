package dev.claucookielabs.kotlinreposapp.reposlist.domain

import dev.claucookielabs.kotlinreposapp.common.domain.BaseRequest
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.UseCase
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper

class GetListOfRepos(private val githubRepository: GithubRepository) :
    UseCase<GetReposRequest, ResultWrapper<List<Repo>>> {

    override suspend fun execute(request: GetReposRequest): ResultWrapper<List<Repo>> {
        return githubRepository.getReposByLanguage(request.languageName)
    }
}

data class GetReposRequest(val languageName: String) : BaseRequest()
