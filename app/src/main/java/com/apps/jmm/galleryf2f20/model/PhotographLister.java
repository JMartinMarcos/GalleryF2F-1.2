package com.apps.jmm.galleryf2f20.model;

import com.apps.jmm.galleryf2f20.pojo.Photo;
import com.apps.jmm.galleryf2f20.utils.FiltroGift;
import com.apps.jmm.galleryf2f20.utils.FiltroImage;
import com.apps.jmm.galleryf2f20.utils.FiltroMovie;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by sath on 16/10/17.
 */

public class PhotographLister {

    private String pathGall, filterName;

    public PhotographLister(String pathGall, String filterName) {
        this.pathGall = pathGall;
        this.filterName = filterName;
    }

    public ArrayList<Photo> photoLister() {

        ArrayList<Photo> fotos = new ArrayList<>();
        File dir = new File(pathGall);
        File[] listDir;
        boolean existe = dir.exists();

        switch (filterName){
            case "IMG":
                listDir = dir.listFiles(new FiltroImage());
                break;
            case "GIF":
                listDir = dir.listFiles(new FiltroGift());

                break;
            case "MOV":
                listDir = dir.listFiles(new FiltroMovie());
                break;
             default:
                 listDir = dir.listFiles();

                 break;
        }


        if (existe) {
            for (File f : listDir) {
                Photo photo = new Photo();
                photo.setImagen(f.getAbsolutePath());
                photo.setNombre(f.getName());
                photo.setFechUltMod(f.lastModified());
                fotos.add(photo);
            }

            Collections.sort(fotos, new Comparator<Photo>() {
                @Override
                public int compare(Photo o1, Photo o2) {

                    return new Long(o2.getFechUltMod()).compareTo(new Long(o1.getFechUltMod()));
                }
            });
        }
        return fotos;
    }
}
