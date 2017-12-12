package com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.FullScreenActivity;
import com.apps.jmm.galleryf2f20.FullScreenVideo.FullscreenActivityVideo;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.IRecycler;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.GallAdapter;
import com.apps.jmm.galleryf2f20.model.PhotographLister;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by sath on 16/10/17.
 */

public class RecyclerPresenter implements IRecyclerPresenter{

    private Activity activity;
    private IRecycler iRecycler;
    private PhotographLister itemList;
    private GallAdapter gallAdapter;
    private ArrayList<Photo> photoList;

    public RecyclerPresenter(final Activity activity, final IRecycler iRecycler, final PhotographLister itemList) {
        this.activity = activity;
        this.iRecycler = iRecycler;
        this.itemList = itemList;
        photoList = itemList.photoLister();
        gallAdapter = iRecycler.createAdapter(photoList);
        loadRecycler();

        gallAdapter.setOnItemClickLister(new GallAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v, int position) {

                Intent intent;

                String url = photoList.get(position).getImagen();

                if (url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".3gp") || url.toLowerCase().endsWith(".mp4") || url.toLowerCase().endsWith(".mkv") || url.toLowerCase().endsWith(".webm"))
                    intent = new Intent(activity, FullscreenActivityVideo.class);
                else
                    intent = new Intent(activity, FullScreenActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("fotos",photoList);
                bundle.putInt("position", position);
                intent.putExtras(bundle);

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public void loadRecycler() {
        iRecycler.AdapterInitialize(gallAdapter);
    }

    @Override
    public void reloadRecycler() {
        iRecycler.AdapterUpdate(gallAdapter,itemList.photoLister());
    }
}
