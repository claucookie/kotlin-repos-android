package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.claucookielabs.kotlinreposapp.common.data.GithubRemoteDataSource
import dev.claucookielabs.kotlinreposapp.common.data.repository.GithubDataRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetListOfRepos
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetReposRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getListOfRepos: GetListOfRepos) : ViewModel() {

    private val _data = MutableLiveData<ReposListUIModel>()
    val data: LiveData<ReposListUIModel>
        get() = _data

    fun fetchKotlinRepos() {
        GlobalScope.launch(Main) {
            _data.value = ReposListUIModel.Loading
            try {
                _data.value = withContext(IO) {
                    ReposListUIModel.Content(
                        getListOfRepos.execute(GetReposRequest("kotlin"))
                    )
                }
            } catch (ex: IllegalStateException) {
                _data.value = ReposListUIModel.Error
            }
        }
    }
}

sealed class ReposListUIModel {
    object Loading : ReposListUIModel()
    object Error : ReposListUIModel()
    class Content(val repos: List<Repo>) : ReposListUIModel()
}

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = GithubDataRepository(GithubRemoteDataSource())
        val useCase = GetListOfRepos(repository)
        return MainViewModel(useCase) as T
    }

}
