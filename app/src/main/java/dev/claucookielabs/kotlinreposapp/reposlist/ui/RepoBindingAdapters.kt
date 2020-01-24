package dev.claucookielabs.kotlinreposapp.reposlist.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.ReposListUIModel

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

@BindingAdapter("loading")
fun View.setLoadingVisibility(uiModel: ReposListUIModel?) {
    uiModel?.let {
        visibility = if (it is ReposListUIModel.Loading) VISIBLE else GONE
    }
}

@BindingAdapter("onError")
fun View.setOnErrorVisibility(uiModel: ReposListUIModel?) {
    uiModel?.let {
        visibility = if (it is ReposListUIModel.Error) VISIBLE else GONE
    }
}
