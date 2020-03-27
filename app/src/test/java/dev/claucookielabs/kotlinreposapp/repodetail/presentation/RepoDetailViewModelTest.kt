package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.common.domain.model.Owner
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.common.utils.CoroutinesDispatcher
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileContent
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepoDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var mockUseCase: GetReadmeFileContent
    @Mock
    private lateinit var mockCoroutinesDispatcher: CoroutinesDispatcher
    @Mock
    private lateinit var liveDataObserver: Observer<UIModel>

    private lateinit var viewModel: RepoDetailViewModel


    @Before
    fun setUp() {
        whenever(mockCoroutinesDispatcher.io()).thenReturn(Dispatchers.Unconfined)
        whenever(mockCoroutinesDispatcher.ui()).thenReturn(Dispatchers.Unconfined)
        viewModel = RepoDetailViewModel(mockUseCase, mockCoroutinesDispatcher)
    }

    @Test
    fun `liveData is initialized with Loading state`() {
        val repo = Repo("kotlin", "kotlin", "1", Owner("kotlin", "url"))
        runBlocking {
            viewModel.readme.observeForever(liveDataObserver)

            viewModel.fetchReadmeContent(repo)

            verify(liveDataObserver).onChanged(UIModel.Loading)
        }
    }

    @Test
    fun `fetchKotlinRepos calls GetReadmeFileContent usecase`() {
        val repo = Repo("kotlin", "kotlin", "1", Owner("kotlin", "url"))
        val resultWrapper = ResultWrapper.Success("")
        runBlocking {

            whenever(mockUseCase.execute(any())).thenReturn(
                resultWrapper
            )

            viewModel.readme.observeForever(liveDataObserver)

            viewModel.fetchReadmeContent(repo)

            verify(mockUseCase).execute(any())
        }
    }

    @Test
    fun `liveData changes to Error state`() {
        val repo = Repo("kotlin", "kotlin", "1", Owner("kotlin", "url"))
        runBlocking {
            viewModel.readme.observeForever(liveDataObserver)

            viewModel.fetchReadmeContent(repo)

            verify(liveDataObserver).onChanged(UIModel.Error)
        }
    }
}