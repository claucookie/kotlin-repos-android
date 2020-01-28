package dev.claucookielabs.kotlinreposapp.common.presentation.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import dev.claucookielabs.kotlinreposapp.R
import dev.claucookielabs.kotlinreposapp.reposlist.presentation.UIModel
import us.feras.mdv.MarkdownView

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
fun MarkdownView.setTextContent(uiModel: UIModel?) {
    if (uiModel is UIModel.Loading) {
        loadMarkdown(resources.getString(R.string.loading_content))
    }
    (uiModel as? UIModel.Content<String>)?.data?.let {
        val content = if (it.isBlank()) resources.getString(R.string.an_error_occured) else it
        loadMarkdown(content)
    }
}
