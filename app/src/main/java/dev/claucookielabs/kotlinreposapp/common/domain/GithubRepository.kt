package dev.claucookielabs.kotlinreposapp.common.domain

import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo

interface GithubRepository {

    fun getReposByLanguage(languageName: String): List<Repo>
}
