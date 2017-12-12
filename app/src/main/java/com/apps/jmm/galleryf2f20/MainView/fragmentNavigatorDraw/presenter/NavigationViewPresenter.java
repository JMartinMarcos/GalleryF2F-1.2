package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.presenter;

import android.content.Context;
import android.view.View;

import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.INavigatorView;
import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.adapter.NavAdapterRV;
import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.asyncProcess.AsyncFindMediaFolders;
import com.apps.jmm.galleryf2f20.model.MakeModelFolders;
import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.util.ArrayList;

/**
 * Created by sath on 5/09/17.
 */

public class NavigationViewPresenter implements INavigationViewPresenter{

    private Context context;
    private INavigatorView iNavigatorView;

    public NavigationViewPresenter(INavigatorView iNavigatorView, Context context) {
        this.iNavigatorView = iNavigatorView;
        this.context = context;
        showFolderRecycler();
    }

    @Override
    public ArrayList<Folder> getFolderList() {
        ArrayList<Folder> folders= new ArrayList<>();
        Folder folder = new Folder();
        folders.add(folder);
        MakeModelFolders makeModelFolders = new MakeModelFolders(context);
        folders.addAll(makeModelFolders.getDataBaseFolders());
        folders.add(folder);
        new AsyncFindMediaFolders().execute(makeModelFolders);
        return folders;
    }

    @Override
    public void showFolderRecycler() {
        NavAdapterRV navAdapterRV = iNavigatorView.createAdapter(getFolderList());
        iNavigatorView.initializeAdapter(navAdapterRV);
        iNavigatorView.configLinearRecyclerView();

        navAdapterRV.setOnItemClickLister(new NavAdapterRV.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v,Folder folder) {
                iNavigatorView.selectedItemDrawer(folder);
            }

            @Override
            public void onItemDeleteSelected(View v, String idFolder) {
                iNavigatorView.removeItemSelected(idFolder);
            }
        });
    }
}
