package dev.claucookielabs.kotlinreposapp.repodetail.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.claucookielabs.kotlinreposapp.R
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.presentation.utils.REPO_EXTRA
import dev.claucookielabs.kotlinreposapp.databinding.ActivityRepoDetailBinding

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
        val repo = intent.getParcelableExtra<Repo>(REPO_EXTRA)
        setupDataBinding(repo!!)
        setupToolbar(repo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar(repo: Repo) {
        supportActionBar?.title = repo.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDataBinding(repo: Repo) {
        val binding: ActivityRepoDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)
        binding.repo = repo
    }
}
