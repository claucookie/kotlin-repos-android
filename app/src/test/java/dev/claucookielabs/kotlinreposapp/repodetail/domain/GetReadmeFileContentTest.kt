package dev.claucookielabs.kotlinreposapp.repodetail.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetReadmeFileContentTest {

    @Mock
    private lateinit var mockGithubRepository: GithubRepository

    private lateinit var useCase: GetReadmeFileContent

    @Before
    fun setUp() {
        useCase = GetReadmeFileContent(mockGithubRepository)
    }

    @Test
    fun `execute() calls the repository straight away`() {
        runBlocking {

            whenever(mockGithubRepository.getFileContent(any(), any(), any()))
                .thenReturn(ResultWrapper.Success("emptyList()"))

            useCase.execute(GetReadmeFileRequest("kotlin", "kotlin"))

            verify(mockGithubRepository).getFileContent(
                eq("kotlin"),
                eq("kotlin"),
                eq("Readme.md")
            )
        }
    }
}