package com.apps.jmm.galleryf2f20.FullScreenPhotoView.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.fragment.FotoFragment;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

/**
 * Created by sath on 4/05/17.
 */

public class MyPageAdapter extends FragmentPagerAdapter {

    ArrayList<Photo> photos;
    FragmentManager fm;
    private long baseId = 0;

    public MyPageAdapter(FragmentManager fm, ArrayList<Photo> photos) {
        super(fm);
        this.photos = photos;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return FotoFragment.newInstance(photos.get(position),position);
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    public void deleteData(int position){
        photos.remove(position);
        notifyDataSetChanged();
    }

    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return FragmentPagerAdapter.POSITION_NONE;
    }


    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }

    /**
     * Notify that the position of a fragment has been changed.
     * Create a new ID for each position to force recreation of the fragment
     * @param n number of items which have been changed
     */
    public void notifyChangeInPosition(int n) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += getCount() + n;
    }

    public Photo getPhotoInPosition(int position){
        return photos.get(position);
    }
}
