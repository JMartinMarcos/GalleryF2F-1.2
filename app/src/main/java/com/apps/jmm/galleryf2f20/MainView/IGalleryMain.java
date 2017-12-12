package com.apps.jmm.galleryf2f20.MainView;

import android.app.Fragment;

import java.util.ArrayList;

/**
 * Created by sath on 15/11/17.
 */

public interface IGalleryMain {
    void setUpViewPager();
    ArrayList<Fragment> addFragments(String photoPath);
}
