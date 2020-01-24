package dev.claucookielabs.kotlinreposapp.reposlist.ui

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
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

@BindingAdapter("visible")
fun LottieAnimationView.setVisibility(uiModel: ReposListUIModel?) {
    uiModel?.let {
        visibility = if (it is ReposListUIModel.Loading) VISIBLE else GONE
    }
}
