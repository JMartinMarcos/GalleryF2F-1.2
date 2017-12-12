package com.apps.jmm.galleryf2f20.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by sath on 27/07/16.
 */
public class FiltroImage implements FilenameFilter {
    //Clase que se encarga de filtrar los archivos
//para que solo se muestren las imagenes soportadas por el terminal, las que tienen la extensión .jpg etc..
    public boolean accept(File dir, String name) {

        boolean fname = false;

        if (name.toLowerCase().endsWith(".jpg")  ||
            name.toLowerCase().endsWith(".jpeg") ||
            name.toLowerCase().endsWith(".png")  ||
            name.toLowerCase().endsWith(".tif")  ||
            name.toLowerCase().endsWith(".tiff") ||
            name.toLowerCase().endsWith(".ico")  ||
            name.toLowerCase().endsWith(".pcx")  ||
            name.toLowerCase().endsWith(".pic")  ||
            name.toLowerCase().endsWith(".psd")  ||
            name.toLowerCase().endsWith(".tga")  ||
            name.toLowerCase().endsWith(".bmp")  ||
            name.toLowerCase().endsWith(".wmf")) {

            fname = true;
        }

        return fname;
    }
}
