package dev.claucookielabs.kotlinreposapp.reposlist.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun RecyclerView.setItems(uiModel: ReposListUIModel?) {
    (uiModel as? ReposListUIModel.Content)?.repos?.let { items ->
        (adapter as? ReposAdapter)?.let {
            items.let { newItems ->
                it.repos = newItems
                it.notifyDataSetChanged()
            }
        }
    }
}
