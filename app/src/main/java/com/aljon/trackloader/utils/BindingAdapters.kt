package com.aljon.trackloader.utils

import android.media.MediaMetadataRetriever
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aljon.trackloader.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

/**
 * XML binding for loading image from a remote url
 *
 * @param imageView - view that will hold the image
 * @param url - source url to get the image
 *
 * Sample usage in xml:  'app:imageUrl = "https://i.picsum.photos/id/837/200/300.jpg"'
 */
@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .apply(
            RequestOptions().placeholder(R.drawable.ic_loading_animation)
                .error(R.drawable.ic_no_image)
                .fallback(R.drawable.ic_no_image)
                .transform(MultiTransformation(RoundedCorners(10), CenterCrop())))
        .into(imageView)
}

/**
 * XML binding for formatting the last visited date
 *
 * @param textView - view that will hold the formatted text
 * @param lastVisited - last visit date in string format
 */
@BindingAdapter("visited")
fun bindVisited(textView: TextView, lastVisited: String?) {
    lastVisited?.let {
        val formattedLastVisit = textView.context.getString(
            R.string.last_visited,
            lastVisited.toTimeElapsed())

        textView.text = formattedLastVisit
    }
}