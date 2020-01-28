package dev.claucookielabs.kotlinreposapp

import dev.claucookielabs.kotlinreposapp.common.data.datasource.GithubDataSource
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubContentServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubRemoteDataSource
import dev.claucookielabs.kotlinreposapp.common.data.datasource.remote.GithubServiceFactory
import dev.claucookielabs.kotlinreposapp.common.data.repository.GithubDataRepository
import dev.claucookielabs.kotlinreposapp.common.domain.GithubRepository
import dev.claucookielabs.kotlinreposapp.repodetail.domain.GetReadmeFileContent
import dev.claucookielabs.kotlinreposapp.repodetail.presentation.RepoDetailActivity
import dev.claucookielabs.kotlinreposapp.repodetail.presentation.RepoDetailViewModel
import dev.claucookielabs.kotlinreposapp.reposlist.domain.GetListOfRepos
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.MainActivity
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun App.initKoin() {
    startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@initKoin)
        modules(listOf(appModule))
    }
}

private val appModule = module {
    single { GithubServiceFactory.create() }
    single { GithubContentServiceFactory.create() }
    factory<GithubRepository> { GithubDataRepository(get()) }
    factory<GithubDataSource> { GithubRemoteDataSource(get(), get()) }

    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetListOfRepos(get()) }
    }

    scope(named<RepoDetailActivity>()) {
        viewModel { RepoDetailViewModel(get()) }
        scoped { GetReadmeFileContent(get()) }
    }
}
