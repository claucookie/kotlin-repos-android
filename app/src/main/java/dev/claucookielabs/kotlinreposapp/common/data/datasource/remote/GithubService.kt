package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import com.squareup.moshi.Json
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiFile
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    suspend fun fetchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Response<SearchResponse>

    @GET("repos/{owner}/{repo}/contents/")
    suspend fun fetchRepositoryContents(
        @Path("owner") ownerName: String,
        @Path("repo") repoName: String
    ): Response<List<ApiFile>>
}

class SearchResponse(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "items") val items: List<ApiRepo>
)
