package dev.claucookielabs.kotlinreposapp.reposlist.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetListOfReposTest {

    @Mock
    private lateinit var mockGithubRepository: GithubRepository

    private lateinit var useCase: GetListOfRepos

    @Before
    fun setUp() {
        useCase = GetListOfRepos(mockGithubRepository)
    }

    @Test
    fun `execute() calls the repository straight away`() {
        runBlocking {

            whenever(mockGithubRepository.getReposByLanguage(any()))
                .thenReturn(ResultWrapper.Success(emptyList()))

            useCase.execute(GetReposRequest("kotlin"))

            verify(mockGithubRepository).getReposByLanguage(eq("kotlin"))
        }
    }
}