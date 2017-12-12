package com.apps.jmm.galleryf2f20.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by sath on 4/11/17.
 */

public class MultimediaFilter implements FilenameFilter {
    @Override
    public boolean accept(File file, String name) {

        String nameDir = file.getAbsolutePath();
        File arch = new File(nameDir + File.separator + name);

        boolean fName = arch.isFile() &&
        //* Formatos de imagen
        (name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".tif") ||
                name.toLowerCase().endsWith(".tiff") ||
                name.toLowerCase().endsWith(".ico") ||
                name.toLowerCase().endsWith(".pcx") ||
                name.toLowerCase().endsWith(".pic") ||
                name.toLowerCase().endsWith(".psd") ||
                name.toLowerCase().endsWith(".tga") ||
                name.toLowerCase().endsWith(".bmp") ||
                name.toLowerCase().endsWith(".wmf") ||

                //* Formatos de imagen animada

                name.toLowerCase().endsWith(".gif") ||

                //* Formatos de video

                name.toLowerCase().endsWith(".avi") ||
                name.toLowerCase().endsWith(".divx") ||
                name.toLowerCase().endsWith(".flv") ||
                name.toLowerCase().endsWith(".mpeg") ||
                name.toLowerCase().endsWith(".wmv") ||
                name.toLowerCase().endsWith(".tiff") ||
                name.toLowerCase().endsWith(".asf") ||
                name.toLowerCase().endsWith(".mp4"));


        boolean hiden = arch.isDirectory() && !name.startsWith(".",0);
        boolean result = false;

        if (hiden || fName){
            result = true;
        }

        return result;

    }
}
