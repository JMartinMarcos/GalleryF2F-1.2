package com.apps.jmm.galleryf2f20.FullScreenVideo.presenter;

import com.apps.jmm.galleryf2f20.FullScreenVideo.IFullScreenActivityVideo;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by sath on 7/11/17.
 */

public class FullScreenVideoPresenter implements IFullScreenVideoPresenter{

    private IFullScreenActivityVideo iFullScreenActivityVideo;
    private ArrayList<Photo> videos;
    int position;

    public FullScreenVideoPresenter(IFullScreenActivityVideo iFullScreenActivityVideo, ArrayList<Photo> videos, int position) {
        this.iFullScreenActivityVideo = iFullScreenActivityVideo;
        this.videos = videos;
        this.position = position;
        loadVideoView();
    }

    @Override
    public void loadVideoView(){
        iFullScreenActivityVideo.loadViewPger(videos,position);
    }

    @Override
    public void sharedVideo() {
        iFullScreenActivityVideo.sharedVid();
    }

    @Override
    public void editVideo() {
        iFullScreenActivityVideo.editVid();
    }

    @Override
    public void removeVideo() {
        iFullScreenActivityVideo.deleteVid();
    }

}
