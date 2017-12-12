package com.apps.jmm.galleryf2f20.MainView;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.NavigatorView;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.RecyclerFragment;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.PageAdapter;
import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.bases.BaseActivity;
import com.apps.jmm.galleryf2f20.global.VariablesGlobales;
import com.apps.jmm.galleryf2f20.model.DataBase;
import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

public class GalleryMain extends BaseActivity implements IGalleryMain {

    private static final String EXTRA_TITLE = "GallF2F";
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
/*    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;*/
    PageAdapter pageAdapter;
    //  private CollapsingToolbarLayout collapsingToolbarLayout;
    boolean drawerSpander = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        solicitarPermisos();

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // addFragment(R.id.content_main, new RecyclerFragment().newInstance(VariablesGlobales.PATH_GALL),this);
        addFragment(R.id.nav_layout, new NavigatorView(), this);

        setupToolbar();

    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpViewPager();
    }

    @Override
    public ArrayList<Fragment> addFragments(String photoPath) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerFragment().newInstance(photoPath, "IMG"));
        fragments.add(new RecyclerFragment().newInstance(photoPath, "GIF"));
        fragments.add(new RecyclerFragment().newInstance(photoPath, "MOV"));

        return fragments;
    }

    @Override
    public void setUpViewPager() {

        if (VariablesGlobales.PATH_GALL.isEmpty()) {
            DataBase db = new DataBase(this);
            Folder folder = db.getFirstFolder();
            VariablesGlobales.PATH_GALL = folder.getPath();
        }
        pageAdapter = new PageAdapter(getFragmentManager(), addFragments(VariablesGlobales.PATH_GALL));
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.camera_diaphragm);
        tabLayout.getTabAt(1).setIcon(R.drawable.visibility_button);
        tabLayout.getTabAt(2).setIcon(R.drawable.film_roll);
    }

    public int getLayoutId() {
        return R.layout.activity_gallery_main;
    }

    public void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

/*    @OnClick(R.id.fab)
    public void callFolderNavegator(){
        Intent intent = new Intent(GalleryMain.this, Navegador_archivos.class);
        startActivity(intent);
    }*/

    @Override
    public void onBackPressed() {

        if (drawerSpander) {
            drawer.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void solicitarPermisos() {
        final int PERMISO_STORAGE_READ = 1;
        //    final int MI_PERMISO_STORAGE2 = 2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_STORAGE_READ);
                SystemClock.sleep(5000);
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_STORAGE_READ);
                    SystemClock.sleep(5000);
                }
            }
        }
    }
}
