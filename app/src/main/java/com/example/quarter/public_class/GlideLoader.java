package com.example.quarter.public_class;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quarter.HomeActivity;
import com.example.quarter.R;
import com.yancy.imageselector.ImageLoader;

/**
 * Created by 祝文 on 2017/11/29.
 */

public class GlideLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
       /* Glide.with(context)
                .load(path)
                .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);*/
        RequestOptions options=new RequestOptions().placeholder(R.mipmap.ic_launcher_round);
        Glide.with(context).load(path)
                .apply(options).into(imageView);
    }
}
