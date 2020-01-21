package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.claucookielabs.kotlinreposapp.R

/**
 * This class will display a list of repositories using Kotlin language.
 * The list will be sorted by stars in descending order (more starred first)
 * Each row will show a brief description of each repository:
 * - Thumbnail
 * - Name of the repo
 * - Descriptions
 * - Number of stars
 *
 * @startuml
 * MainActivity --|> AppCompatActivity
 * @enduml
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel> { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadRepos()
    }

    private fun loadRepos() {
        viewModel.fetchKotlinRepos()
    }
}
