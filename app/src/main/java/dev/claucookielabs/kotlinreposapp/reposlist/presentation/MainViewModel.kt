package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.claucookielabs.kotlinreposapp.common.domain.model.ResultWrapper
import dev.claucookielabs.kotlinreposapp.common.utils.CoroutinesDispatcher
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetListOfRepos
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetReposRequest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getListOfRepos: GetListOfRepos,
    private val dispatcher: CoroutinesDispatcher
) : ViewModel() {

    private val _data = MutableLiveData<UIModel>()
    val data: LiveData<UIModel>
        get() = _data

    fun fetchKotlinRepos() {
        if (_data.value != null) return

        viewModelScope.launch(dispatcher.ui()) {
            _data.value = UIModel.Loading
            _data.value = withContext(dispatcher.io()) {
                val result = getListOfRepos.execute(GetReposRequest("kotlin"))
                when (result) {
                    is ResultWrapper.Success -> UIModel.Content(result.value)
                    else -> UIModel.Error
                }
            }
        }
    }
}
