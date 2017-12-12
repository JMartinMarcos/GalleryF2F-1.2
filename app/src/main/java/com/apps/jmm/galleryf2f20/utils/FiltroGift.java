package com.apps.jmm.galleryf2f20.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by sath on 4/11/17.
 */

public class FiltroGift implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {

        boolean fname = false;

        if (name.toLowerCase().endsWith(".gif"))
            fname = true;
        return fname;
    }
}
