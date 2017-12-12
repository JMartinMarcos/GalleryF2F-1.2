package com.apps.jmm.galleryf2f20.restApi.model;

import com.apps.jmm.galleryf2f20.pojo.Image;

import java.util.ArrayList;

/**
 * Created by sath on 18/01/17.
 */

public class ImageResponse {

    ArrayList<Image> images;

    public ArrayList<Image> getMascotas() {
        return images;
    }

    public void setMascotas(ArrayList<Image> mascotas) {
        this.images = mascotas;
    }
}
