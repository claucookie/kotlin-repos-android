package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.claucookielabs.kotlinreposapp.R

/**
 * This class will display the complete information for a repository.
 *
 * https://plantuml.com/class-diagram
 * Android Studio plugin: https://plugins.jetbrains.com/plugin/7017-plantuml-integration/
 *
 * @startuml
 * RepoDetailActivity --|> AppCompatActivity
 * @enduml
 */
class RepoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)
    }
}
