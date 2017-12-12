package com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain;

import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.GallAdapter;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by sath on 17/03/17.
 */

public interface IRecycler {

    public void configureLayout();

    public GallAdapter createAdapter(ArrayList<Photo> photos);

    public void AdapterInitialize(GallAdapter gAdapter);

    public void AdapterUpdate(GallAdapter gAdapter, ArrayList<Photo> listPhoto);

    }
