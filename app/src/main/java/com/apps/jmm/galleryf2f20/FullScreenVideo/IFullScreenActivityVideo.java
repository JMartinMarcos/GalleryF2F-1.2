package com.apps.jmm.galleryf2f20.FullScreenVideo;

import android.net.Uri;
import android.support.v4.app.FragmentManager;

import com.apps.jmm.galleryf2f20.FullScreenVideo.adapter.VideoPageAdapter;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.io.File;
import java.util.ArrayList;


public interface IFullScreenActivityVideo {

    void loadViewPger(ArrayList<Photo> fotos, int position);

    VideoPageAdapter buildAdapterViewPagerVideos(ArrayList<Photo> photos, FragmentManager fm);

    Uri getUriForFilePath();

    void sharedVid();

    void editVid();

    void deleteVid();

    void deleteVid(File file, int imagePos);
}


