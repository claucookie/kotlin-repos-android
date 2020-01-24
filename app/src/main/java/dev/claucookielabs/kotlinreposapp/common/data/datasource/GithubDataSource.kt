package dev.claucookielabs.kotlinreposapp.common.data.datasource

import dev.claucookielabs.kotlinreposapp.common.data.model.ApiRepo

interface GithubDataSource {

    fun getReposByLanguage(languageName: String) : List<ApiRepo>
}
