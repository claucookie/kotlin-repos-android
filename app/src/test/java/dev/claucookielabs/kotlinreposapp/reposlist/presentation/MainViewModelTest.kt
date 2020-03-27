package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.common.utils.CoroutinesDispatcher
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetListOfRepos
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetReposRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MainViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var mockUseCase: GetListOfRepos
    @Mock
    private lateinit var mockCoroutinesDispatcher: CoroutinesDispatcher
    @Mock
    private lateinit var liveDataObserver: Observer<UIModel>

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        whenever(mockCoroutinesDispatcher.ioDispatcher()).thenReturn(Dispatchers.Unconfined)
        whenever(mockCoroutinesDispatcher.uiDispatcher()).thenReturn(Dispatchers.Unconfined)
        viewModel = MainViewModel(mockUseCase, mockCoroutinesDispatcher)
    }

    @Test
    fun `liveData is initialized with Loading state`() {
        val repos = emptyList<Repo>()
        val result = ResultWrapper.Success(repos)
        runBlocking {
            whenever(mockUseCase.execute(eq(GetReposRequest("kotlin")))).thenReturn(
                result
            )
            viewModel.data.observeForever(liveDataObserver)

            viewModel.fetchKotlinRepos()

            verify(liveDataObserver).onChanged(UIModel.Loading)
        }
    }

    @Test
    fun `fetchKotlinRepos calls GetListOfRepos usecase`() {
        runBlocking {
            whenever(mockUseCase.execute(eq(GetReposRequest("kotlin")))).thenReturn(
                ResultWrapper.Success(emptyList())
            )
            viewModel.fetchKotlinRepos()

            verify(mockUseCase).execute(any())
        }
    }

    @Test
    fun `liveData changes to Error state`() {
        val result = ResultWrapper.GenericError(503, "Server Error")
        runBlocking {
            whenever(mockUseCase.execute(eq(GetReposRequest("kotlin")))).thenReturn(
                result
            )
            viewModel.data.observeForever(liveDataObserver)

            viewModel.fetchKotlinRepos()

            verify(liveDataObserver).onChanged(UIModel.Error)
        }
    }
}