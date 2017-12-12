package com.apps.jmm.galleryf2f20.utils;

/**
 * Created by sath on 4/11/17.
 */

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by sath on 4/11/17.
 */

public class FiltroMovie implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {

        boolean fname = false;

        if (name.toLowerCase().endsWith(".avi")  ||
            name.toLowerCase().endsWith(".divx") ||
            name.toLowerCase().endsWith(".flv")  ||
            name.toLowerCase().endsWith(".mpeg") ||
            name.toLowerCase().endsWith(".wmv")  ||
            name.toLowerCase().endsWith(".tiff") ||
            name.toLowerCase().endsWith(".asf")  ||
            name.toLowerCase().endsWith(".mp4"))

            fname = true;

        return fname;
    }
}


