package ru.poetofcode.whatahorror.presentation

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import ru.poetofcode.whatahorror.R

class BindingAdapters {

    companion object {

        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(imageView: ImageView, imageUrl: String) {
            Log.d("log", "LoadImage invoked, imageUrl: $imageUrl")
            // Picasso.get().isLoggingEnabled = true
            // Picasso.get().setIndicatorsEnabled(true)
            Picasso.get()
                .load(imageUrl)
                .resizeDimen(R.dimen.imageWidth, R.dimen.imageHeight)
                .centerCrop()
                // .placeholder(R.drawable.user_placeholder)
                // .error(R.drawable.user_placeholder_error)
                .into(imageView)
        }
    }
}