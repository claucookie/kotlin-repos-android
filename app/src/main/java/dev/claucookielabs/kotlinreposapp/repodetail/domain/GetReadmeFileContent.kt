package dev.claucookielabs.kotlinreposapp.repodetail.domain

import dev.claucookielabs.kotlinreposapp.common.domain.BaseRequest
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.UseCase
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper

class GetReadmeFileContent(private val githubRepository: GithubRepository) :
    UseCase<GetReadmeFileRequest, ResultWrapper<String>> {

    override suspend fun execute(request: GetReadmeFileRequest): ResultWrapper<String> {
        return githubRepository.getReadmeFileContent(request.ownerName, request.repoName)
    }
}

class GetReadmeFileRequest(val ownerName: String, val repoName: String) : BaseRequest()
