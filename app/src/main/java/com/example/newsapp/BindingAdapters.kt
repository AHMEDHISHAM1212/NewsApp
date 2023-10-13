package com.example.newsapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:url","placeHolder"] , requireAll = false)
fun loadImageFromUrl(imageView: ImageView,
                     url : String?,
                     placeholder: Drawable?) {
    Glide.with(imageView)
        .load(url)
        .placeholder(placeholder)
        .into(imageView)
}


@BindingAdapter("onClickViews")
fun onClickListenerViews(view:View?
                         ,url : String? ) {
    view?.setOnClickListener{it
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)

    }

}
