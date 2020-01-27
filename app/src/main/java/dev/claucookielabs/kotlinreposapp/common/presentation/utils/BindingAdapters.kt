package dev.claucookielabs.kotlinreposapp.common.presentation.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.claucookielabs.kotlinreposapp.R
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("loading")
fun View.setLoadingVisibility(uiModel: UIModel?) {
    uiModel?.let {
        visibility = if (it is UIModel.Loading) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("onError")
fun View.setOnErrorVisibility(uiModel: UIModel?) {
    uiModel?.let {
        visibility = if (it is UIModel.Error) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("content")
fun TextView.setTextContent(uiModel: UIModel?) {
    if (uiModel is UIModel.Loading) {
        text = resources.getString(R.string.loading_content)
    }
    (uiModel as? UIModel.Content<String>)?.data?.let {
        text = it
    }
}
