package dev.claucookielabs.kotlinreposapp.common.data.datasource.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiFile
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiOwner
import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GithubRemoteDataSourceTest {

    @Mock
    private lateinit var mockGithubContentService: GithubContentService
    @Mock
    private lateinit var mockGithubService: GithubService

    private lateinit var dataSource: GithubRemoteDataSource

    @Before
    fun setUp() {
        dataSource = GithubRemoteDataSource(mockGithubService, mockGithubContentService)
    }

    @Test
    fun `getReposByLanguage() returns items`() {
        val successResponse = Response.success(
            SearchResponse(aListOfRepos().count(), aListOfRepos())
        )

        runBlocking {
            whenever(mockGithubService.fetchRepositories(any(), any(), any()))
                .thenReturn(successResponse)

            val result = dataSource.getReposByLanguage("kotlin")

            assert(result.count() == 1)
        }
    }

    @Test
    fun `getReposByLanguage() returns empty list if response is null`() {
        val successResponse = Response.success<SearchResponse>(null)

        runBlocking {
            whenever(mockGithubService.fetchRepositories(any(), any(), any()))
                .thenReturn(successResponse)

            val result = dataSource.getReposByLanguage("kotlin")

            assert(result.count() == 0)
        }
    }

    @Test
    fun `getFileContent() returns readme file`() {
        val successResponse = Response.success(aListOfFiles())
        val fileName = "Readme.md"

        runBlocking {
            whenever(mockGithubService.fetchRepositoryContents(any(), any()))
                .thenReturn(successResponse)
            whenever(
                mockGithubContentService.fetchRepositoryFileContent(
                    any(),
                    any(),
                    eq(fileName)
                )
            )
                .thenReturn(Response.success("readme content"))

            val result = dataSource.getFileContent("kotlin", "kotlin", fileName)

            assert(result == "readme content")
        }
    }

    @Test
    fun `getFileContent() returns empty content if file not found`() {
        val successResponse = Response.success(aListOfFiles())
        val fileName = "Readme.md"

        runBlocking {
            whenever(mockGithubService.fetchRepositoryContents(any(), any()))
                .thenReturn(successResponse)
            whenever(
                mockGithubContentService.fetchRepositoryFileContent(
                    any(),
                    any(),
                    eq(fileName)
                )
            ).thenReturn(Response.success(null))

            val result = dataSource.getFileContent("kotlin", "kotlin", fileName)

            assert(result == "")
        }
    }

    private fun aListOfRepos(): List<ApiRepo> {
        return listOf(
            ApiRepo(
                name = "Kotlin repo name",
                description = "Kotlin repo description",
                owner = ApiOwner(
                    login = "username",
                    avatar_url = "url"
                ),
                stargazers_count = 1000
            )
        )
    }

    private fun aListOfFiles(): List<ApiFile> {
        return listOf(
            ApiFile(
                name = "gitignore",
                download_url = "url"
            ),
            ApiFile(
                name = "Readme.md",
                download_url = "url"
            )
        )
    }
}