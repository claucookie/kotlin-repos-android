package dev.claucookielabs.kotlinreposapp.common.presentation.utils

import android.content.Intent
import android.view.View
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.repodetail.presentation.RepoDetailActivity

class Navigator {

    fun navigateToDetailScreen(view: View, repo: Repo) {
        val intent = Intent(view.context, RepoDetailActivity::class.java)
        intent.putExtra(REPO_EXTRA, repo)
        view.context.startActivity(intent)
    }

}

const val REPO_EXTRA = "repo"
