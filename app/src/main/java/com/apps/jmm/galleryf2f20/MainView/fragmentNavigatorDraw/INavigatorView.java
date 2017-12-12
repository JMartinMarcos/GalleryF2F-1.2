package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw;

import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.adapter.NavAdapterRV;
import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.util.ArrayList;

/**
 * Created by sath on 5/09/17.
 */

public interface INavigatorView {
    void configLinearRecyclerView();

    NavAdapterRV createAdapter(ArrayList<Folder> folders);

    void initializeAdapter(NavAdapterRV gAdapter);

    void selectedItemDrawer(Folder folder);

    void removeItemSelected(String idItem);
}
