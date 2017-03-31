package io.feng24k.app.gank.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

import io.feng24k.app.gank.R;


public class PhotoImageView extends TouchImageView {
    public PhotoImageView(Context context) {
        super(context);
    }

    public PhotoImageView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void setImageUrl(String url) {
        Glide.with(this.getContext()).load(url).error(R.drawable.img_default).placeholder(R.drawable.img_default).into(this);
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

}
