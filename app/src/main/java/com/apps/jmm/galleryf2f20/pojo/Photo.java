package com.apps.jmm.galleryf2f20.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sath on 17/03/17.
 */

public class Photo implements Parcelable{
    public Photo() {
    }

    private String imagen;
    private String nombre;
    private Long fechUltMod;


    public Photo(Parcel in) {
        imagen = in.readString();
        nombre = in.readString();
        fechUltMod = in.readLong();

    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getFechUltMod() {
        return fechUltMod;
    }

    public void setFechUltMod(Long fechUltMod) {
        this.fechUltMod = fechUltMod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imagen);
        parcel.writeString(nombre);
        parcel.writeLong(fechUltMod);
    }
}
