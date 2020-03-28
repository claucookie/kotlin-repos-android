package dev.claucookielabs.kotlinreposapp.common.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.aListOfApiRepos
import dev.claucookielabs.kotlinreposapp.aListOfRepos
import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubDataRepositoryTest {

    @Mock
    private lateinit var mockGithubDataSource: GithubDataSource

    private lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        repository = GithubDataRepository(mockGithubDataSource)
    }

    @Test
    fun `getReposByLanguage returns list of repos mapped`() {
        runBlocking {
            whenever(mockGithubDataSource.getReposByLanguage(any()))
                .thenReturn(aListOfApiRepos())

            val mappedResult: ResultWrapper.Success<List<Repo>> =
                repository.getReposByLanguage("kotlin") as ResultWrapper.Success<List<Repo>>

            assertTrue(mappedResult.value == aListOfRepos())
        }
    }

    @Test
    fun `getFileContent() returns the file content mapped`() {
        runBlocking {
            whenever(mockGithubDataSource.getFileContent(any(), any(), any()))
                .thenReturn("content")

            val mappedResult: ResultWrapper.Success<String> =
                repository.getFileContent(
                    "kotlin",
                    "kotlin",
                    "Readme.md"
                ) as ResultWrapper.Success<String>

            assertTrue(mappedResult.value == "content")
        }
    }
}