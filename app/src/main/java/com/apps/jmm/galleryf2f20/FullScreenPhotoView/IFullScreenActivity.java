package com.apps.jmm.galleryf2f20.FullScreenPhotoView;

import android.net.Uri;
import android.support.v4.app.FragmentManager;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.adapter.MyPageAdapter;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sath on 24/10/17.
 */

public interface IFullScreenActivity {

    void loadViewPger(ArrayList<Photo> fotos, int position);

    MyPageAdapter buildAdapterViewPagerPhotos(ArrayList<Photo> photos, FragmentManager fm);

    Uri getUriForFilePath();

    void sharedImg();

    void editImg();

    void deleteImg();

    void deletePhoto(File file, int imagePos);
}
