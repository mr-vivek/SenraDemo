package com.vivek.demo.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vivek.demo.R;
import com.vivek.demo.application.AssignmentApp;

public class CustomSetter {

    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.no_image_placeholder);
        // requestOptions.error(R.drawable.ic_error);

        Glide.with(AssignmentApp.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl).into(imageView);
    }

}