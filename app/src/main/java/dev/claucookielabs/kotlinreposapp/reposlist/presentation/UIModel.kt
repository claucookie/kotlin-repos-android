package dev.claucookielabs.kotlinreposapp.reposlist.presentation

sealed class UIModel {
    object Loading : UIModel()
    object Error : UIModel()
    class Content<T>(val data: T) : UIModel()
}
