package dev.claucookielabs.kotlinreposapp.common.domain

import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo

interface GithubRepository {

    suspend fun getReposByLanguage(languageName: String): List<Repo>
}
