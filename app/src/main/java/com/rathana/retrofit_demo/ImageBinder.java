package com.rathana.retrofit_demo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageBinder {

    public static void bind(Context context, String url, ImageView image) {
        if (url != null) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.ic_favorite_border_red_24dp)
                    .into(image);
        } else {
            //default image
            image.setImageResource(R.drawable.ic_favorite_border_red_24dp);
        }
    }
}
