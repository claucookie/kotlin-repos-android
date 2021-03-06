package dev.claucookielabs.kotlinreposapp.reposlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.claucookielabs.kotlinreposapp.R
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.common.presentation.utils.Navigator
import dev.claucookielabs.kotlinreposapp.databinding.ItemViewRepoBinding

class ReposAdapter : RecyclerView.Adapter<RepoItemViewHolder>() {

    var repos: List<Repo> = emptyList()

    private val navigator = Navigator()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemViewRepoBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_view_repo,
                parent,
                false
            )
        return RepoItemViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        bind(holder.binding, repos[position])
        holder.binding.executePendingBindings()
    }

    private fun bind(binding: ItemViewRepoBinding, repo: Repo) {
        binding.repo = repo
        binding.navigator = navigator
    }
}

class RepoItemViewHolder(val binding: ItemViewRepoBinding) : RecyclerView.ViewHolder(binding.root)
