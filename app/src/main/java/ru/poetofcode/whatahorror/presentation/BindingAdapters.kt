package ru.poetofcode.whatahorror.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    Log.d("log", "LoadImage invoked, imageUrl: $imageUrl")
    // Picasso.get().isLoggingEnabled = true
    // Picasso.get().setIndicatorsEnabled(true)
    Picasso.get()
        .load(imageUrl)
        .resize(imageView.width, imageView.height)
        .centerCrop()
        // .placeholder(R.drawable.user_placeholder)
        // .error(R.drawable.user_placeholder_error)
        .into(imageView)

}

@BindingAdapter(*["app:onTouch"])
fun onTouch(view: View, touchListener: View.OnTouchListener) {
    view.setOnTouchListener(touchListener)
}

@BindingAdapter("android:tint")
fun setImageTint(view: View, @ColorInt color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        view.backgroundTintList = ColorStateList.valueOf(color)
    }
}