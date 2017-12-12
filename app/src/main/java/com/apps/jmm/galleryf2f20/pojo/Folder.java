package com.apps.jmm.galleryf2f20.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sath on 18/04/17.
 */

public class Folder implements Parcelable {
    public Folder() {
    }
    private String id;
    private String path;
    private String nombre;
    private int icono;

    protected Folder(Parcel in) {
        path   = in.readString();
        nombre = in.readString();
        icono  = in.readInt();
        id  = in.readString();

    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel in) {
            return new Folder(in);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(nombre);
        parcel.writeInt(icono);
        parcel.writeString(id);
    }
}
