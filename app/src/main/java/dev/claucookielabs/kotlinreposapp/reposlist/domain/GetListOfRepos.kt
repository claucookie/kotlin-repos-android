package dev.claucookielabs.kotlinreposapp.reposlist.domain

import dev.claucookielabs.kotlinreposapp.common.data.repository.GithubDataRepository
import dev.claucookielabs.kotlinreposapp.common.domain.BaseRequest
import dev.claucookielabs.kotlinreposapp.common.domain.UseCase
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo

class GetListOfRepos(private val githubRepository: GithubDataRepository) :
    UseCase<GetReposRequest, List<Repo>> {

    override suspend fun execute(request: GetReposRequest): List<Repo> {
        return githubRepository.getReposByLanguage(request.languageName)
    }
}

data class GetReposRequest(val languageName: String) : BaseRequest()
