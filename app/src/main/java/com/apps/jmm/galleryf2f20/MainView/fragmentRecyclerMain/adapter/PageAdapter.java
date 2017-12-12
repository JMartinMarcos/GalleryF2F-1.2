package com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by sath on 29/12/16.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void reloadFragments(ArrayList<Fragment> fragments){
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }
}
