package com.apps.jmm.galleryf2f20.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sath on 23/01/17.
 */

public class Usuario implements Parcelable {

    private String id;
    private String fullName;
    private String picture;

    public Usuario() {
    }

    public Usuario(String id, String fullName, String picture) {
        this.id = id;
        this.fullName = fullName;
        this.picture = picture;
    }

    protected Usuario(Parcel in) {
        id = in.readString();
        fullName = in.readString();
        picture = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(fullName);
        parcel.writeString(picture);
    }
}
