package com.apps.jmm.galleryf2f20.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sath on 22/12/16.
 */

public class Image implements Parcelable {

    private String id;
    private String image;
    private String name;
    private String rating;
    private String id_media;

    public Image(String imagen, String name, String rating, String id_media) {
        this.image    = imagen;
        this.name    = name;
        this.rating   = rating;
        this.id_media = id_media;
    }

    protected Image(Parcel in) {
        image    = in.readString();
        name    = in.readString();
        rating   = in.readString();
        id_media = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public Image() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String imagen) {
        this.image = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String raiting) {
        this.rating = raiting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_media() {
        return id_media;
    }

    public void setId_medeia(String id_media) {
        this.id_media = id_media;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(id_media);
    }
}
