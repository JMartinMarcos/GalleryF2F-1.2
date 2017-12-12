package com.apps.jmm.galleryf2f20.FullScreenPhotoView.presenter;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.IFullScreenActivity;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by sath on 3/11/17.
 */

public class FullScreenActivityPresenter implements IFullScreenActivityPresenter {

    private IFullScreenActivity iFullScreenActivity;
    private ArrayList<Photo> photos;
    int position;

    public FullScreenActivityPresenter(IFullScreenActivity iFullScreenActivity, ArrayList<Photo> photos, int position) {
        this.iFullScreenActivity = iFullScreenActivity;
        this.photos = photos;
        this.position = position;
        loadPhotoView();
    }

    @Override
    public void loadPhotoView(){
        iFullScreenActivity.loadViewPger(photos,position);
    }

    @Override
    public void sharedPhoto() {
        iFullScreenActivity.sharedImg();
    }

    @Override
    public void editPhoto() {
        iFullScreenActivity.editImg();
    }

    @Override
    public void removePhoto() {
        iFullScreenActivity.deleteImg();
    }


}