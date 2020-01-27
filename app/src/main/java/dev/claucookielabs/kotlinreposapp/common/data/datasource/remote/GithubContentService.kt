package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubContentService {

    @GET("{owner}/{repo}/master/ReadMe.md")
    suspend fun fetchRepositoryReadme(
        @Path("owner") ownerName: String,
        @Path("repo") repoName: String
    ): Response<String>
}
