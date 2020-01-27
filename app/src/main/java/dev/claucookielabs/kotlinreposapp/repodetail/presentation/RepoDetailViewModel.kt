package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoDetailViewModel : ViewModel() {

    private val _data = MutableLiveData<UIModel>()
    val data: LiveData<UIModel>
        get() = _data

    fun fetchReadmeContent() {
        GlobalScope.launch(Dispatchers.Main) {
            _data.value = UIModel.Loading
            _data.value = withContext(Dispatchers.IO) {
                val readmeResult = ResultWrapper.Success("empty for now")
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
        return RepoDetailViewModel() as T
    }

}
