package dev.claucookielabs.kotlinreposapp.reposlist.domain

import dev.claucookielabs.kotlinreposapp.common.domain.BaseRequest
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.UseCase

class GetListOfRepos : UseCase<GetReposRequest, List<Repo>> {
    override suspend fun execute(request: GetReposRequest): List<Repo> {
        return listOf(
            Repo(
                name = "Android",
                description = "This is a short description",
                thumbnailUrl = "https://avatars3.githubusercontent.com/u/32689599?v=4",
                starsCount = "99"
            )
        )
    }
}

data class GetReposRequest(val languageName: String) : BaseRequest()
