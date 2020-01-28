package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubContentServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubRemoteDataSource
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.repository.GithubDataRepository
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileContent
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileRequest
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoDetailViewModel(private val getReadmeFileContent: GetReadmeFileContent) : ViewModel() {

    private val _readme = MutableLiveData<UIModel>()
    val readme: LiveData<UIModel>
        get() = _readme

    fun fetchReadmeContent(repo: Repo) {
        GlobalScope.launch(Dispatchers.Main) {
            _readme.value = UIModel.Loading
            _readme.value = withContext(Dispatchers.IO) {
                val readmeResult = getReadmeFileContent.execute(
                    GetReadmeFileRequest(
                        repo.owner.userName,
                        repo.name
                    )
                )
                when (readmeResult) {
                    is ResultWrapper.Success -> UIModel.Content(readmeResult.value)
                    else -> UIModel.Error
                }
            }
        }
    }
}

class RepoDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val remoteDataSource = GithubRemoteDataSource(
            GithubServiceFactory.makeGithubService(),
            GithubContentServiceFactory.makeGithubContentService()
        )
        val githubRepository = GithubDataRepository(remoteDataSource)
        val getReadmeFileContent =
            GetReadmeFileContent(
                githubRepository
            )
        return RepoDetailViewModel(getReadmeFileContent) as T
    }

}
