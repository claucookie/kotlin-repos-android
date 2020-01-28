package dev.claucookielabs.kotlinreposapp.reposlist.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.claucookielabs.kotlinreposapp.common.domain.model.Repo
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel

@BindingAdapter("repos")
fun RecyclerView.setRepos(uiModel: UIModel?) {
    (uiModel as? UIModel.Content<List<Repo>>)?.data?.let { items ->
        (adapter as? ReposAdapter)?.let {
            items.let { newItems ->
                it.repos = newItems
                it.notifyDataSetChanged()
            }
        }
    }
}
