package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.claucookielabs.kotlinreposapp.R
import dev.claucookielabs.kotlinreposapp.databinding.ActivityMainBinding
import dev.claucookielabs.kotlinreposapp.reposlist.ui.ReposAdapter

/**
 * This class will display a list of repositories using Kotlin language.
 * The list will be sorted by stars in descending order (more starred first)
 * Each row will show a brief description of each repository:
 * - Thumbnail
 * - Name of the repo
 * - Descriptions
 * - Number of stars
 *
 * https://plantuml.com/class-diagram
 * Android Studio plugin: https://plugins.jetbrains.com/plugin/7017-plantuml-integration/
 *
 * @startuml
 * MainActivity --|> AppCompatActivity
 * MainActivity --* MainViewModel
 * MainViewModel --* GetGithubReposUseCase
 * GetGithubReposUseCase --* GithubRepository
 * @enduml
 */
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDatabinding()
        loadRepos()
    }

    private fun setupDatabinding() {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewmodel = mainViewModel
            lifecycleOwner = this@MainActivity
            reposRv.adapter =
                ReposAdapter()
        }
    }

    private fun loadRepos() {
        mainViewModel.fetchKotlinRepos()
    }
}
