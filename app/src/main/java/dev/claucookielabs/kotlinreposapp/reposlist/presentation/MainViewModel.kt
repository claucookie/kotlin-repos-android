package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.claucookielabs.kotlinreposapp.reposlist.model.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _data = MutableLiveData<ReposListUIModel>()
    val data: LiveData<ReposListUIModel>
        get() = _data

    fun fetchKotlinRepos() {
        GlobalScope.launch(Main) {
            _data.value = ReposListUIModel.Loading
            try {
                _data.value = withContext(IO) { ReposListUIModel.Content(emptyList()) }
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
        return MainViewModel() as T
    }

}
