package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apps.jmm.galleryf2f20.MainView.GalleryMain;
import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.adapter.NavAdapterRV;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.SampleDivider;
import com.apps.jmm.galleryf2f20.bases.BaseFragment;
import com.apps.jmm.galleryf2f20.decoration.HeaderDecoration;
import com.apps.jmm.galleryf2f20.global.VariablesGlobales;
import com.apps.jmm.galleryf2f20.model.DataBase;
import com.apps.jmm.galleryf2f20.model.MakeModelFolders;
import com.apps.jmm.galleryf2f20.pojo.Folder;
import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.presenter.INavigationViewPresenter;
import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.presenter.NavigationViewPresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by sath on 6/09/17.
 */

public class NavigatorView extends BaseFragment implements INavigatorView {

    @BindView(R.id.rv_navigator)
      RecyclerView listArch;

    private INavigationViewPresenter presenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = new NavigationViewPresenter(this,getActivity());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_navigator_view;
    }

    @Override
    public void configLinearRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listArch.setHasFixedSize(true);
        listArch.setLayoutManager(llm);
        final RecyclerView.ItemDecoration itemDecoration = new SampleDivider(getActivity());
        listArch.addItemDecoration(itemDecoration);
    }

    @Override
    public NavAdapterRV createAdapter(ArrayList<Folder> folders) {
        return new NavAdapterRV(folders,getActivity(),getActivity());
    }

    @Override
    public void initializeAdapter(NavAdapterRV gAdapter) {
        listArch.setAdapter(gAdapter);
/*        listArch.addItemDecoration(HeaderDecoration.with(listArch)
                .inflate(R.layout.nav_header_main)
                .parallax(0.2f)
                .dropShadowDp(4)
                .build());*/

    }

    @Override
    public void selectedItemDrawer(Folder folder){
        VariablesGlobales.PATH_GALL = folder.getPath();
        getActivity().onBackPressed();
        callMainLayout(folder.getPath());
        DrawerLayout drawerLayout;
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
    }

    public void callMainLayout(String photoPath) {
        Intent intent = new Intent(getActivity(), GalleryMain.class);
        intent.putExtra("PHOTO", photoPath);
        getActivity().startActivity(intent);
    }

    public void removeItemSelected(String idItem){
        MakeModelFolders carpet = new MakeModelFolders(getActivity());
        DataBase db = new DataBase(getActivity());
        carpet.removeFolder(db, Integer.parseInt(idItem));
    }

}