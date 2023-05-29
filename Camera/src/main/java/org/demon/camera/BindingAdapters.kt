package org.demon.camera

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl1")
fun ImageView.imageUrl1(url: String?) {
    if (url != null) Glide.with(context).load(url).into(this)
}

@BindingAdapter("imageUrl2")
fun ImageView.imageUrl2(url: String) = Glide.with(this.context).load(url).into(this)

@BindingAdapter("imageUrl3")
fun ImageView.imageUrl3(url: Any?) {
    if (url != null) Glide.with(context).load(url).into(this)
}



//Glide.with(this)
//.load(service.getImageUri())
//.dontAnimate()
//.error(R.drawable.error_img)
//// 设置高斯模糊
//.bitmapTransform(new BlurTransformation(this, 14, 3))
//.into(imageview);