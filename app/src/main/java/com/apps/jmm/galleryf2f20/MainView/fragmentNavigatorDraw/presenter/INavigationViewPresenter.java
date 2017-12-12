package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.presenter;

import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.util.ArrayList;

/**
 * Created by sath on 5/09/17.
 */

public interface INavigationViewPresenter {

    ArrayList<Folder> getFolderList();

    void showFolderRecycler();

}
