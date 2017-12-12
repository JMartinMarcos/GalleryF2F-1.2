package com.apps.jmm.galleryf2f20.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.GlideApp;
import com.apps.jmm.galleryf2f20.R;
import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by sath on 4/11/17.
 */

public class GlideAppSwitch {
    Activity activity;
    String url;
    ImageView view;

    public GlideAppSwitch(Activity activity, String url, ImageView view) {
        this.url = url;
        this.activity = activity;
        this.view = view;
    }

    public void GlideAppLoad() {

        switch (url.toLowerCase()) {
            case ".gif":


                GlideApp.with(activity)
                        .asGif()
                        .load(url)
                        .thumbnail(0.5f)
                        .error(R.drawable.bmp_file_256)
                        .fitCenter()
                        .into(view);

                break;
            case ".avi":
            case ".3gp":
            case ".flv":
            case ".mkv":
            case ".webm":
            case ".mp4":

                GlideApp.with(activity)
                        .asBitmap()
                        .load(Uri.fromFile(new File(url)))
                        .into(view);
                break;
            default:


                Glide.with(activity)
                        .load(url)
                        .thumbnail(0.5f)
                        .into(view);
                break;
        }
    }
}
