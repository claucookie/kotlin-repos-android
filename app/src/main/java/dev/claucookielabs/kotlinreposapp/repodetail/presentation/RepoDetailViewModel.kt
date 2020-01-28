package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileContent
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileRequest
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoDetailViewModel(private val getReadmeFileContent: GetReadmeFileContent) : ViewModel() {

    private val _readme = MutableLiveData<UIModel>()
    val readme: LiveData<UIModel>
        get() = _readme

    fun fetchReadmeContent(repo: Repo) {
        if (_readme.value != null) return

        viewModelScope.launch {
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
