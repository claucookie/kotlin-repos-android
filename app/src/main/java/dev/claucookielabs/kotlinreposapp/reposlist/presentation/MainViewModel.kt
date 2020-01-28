package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubContentServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubRemoteDataSource
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.repository.GithubDataRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetListOfRepos
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetReposRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getListOfRepos: GetListOfRepos) : ViewModel() {

    private val _data = MutableLiveData<UIModel>()
    val data: LiveData<UIModel>
        get() = _data

    fun fetchKotlinRepos() {
        GlobalScope.launch(Main) {
            _data.value = UIModel.Loading
            _data.value = withContext(IO) {
                val result = getListOfRepos.execute(GetReposRequest("kotlin"))
                when (result) {
                    is ResultWrapper.Success -> UIModel.Content(result.value)
                    else -> UIModel.Error
                }
            }
        }
    }
}

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val remoteDataSource = GithubRemoteDataSource(
            GithubServiceFactory.create(),
            GithubContentServiceFactory.create()
        )
        val repository = GithubDataRepository(remoteDataSource)
        val useCase = GetListOfRepos(repository)
        return MainViewModel(useCase) as T
    }

}
