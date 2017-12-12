package com.apps.jmm.galleryf2f20.restApi.model;


import com.apps.jmm.galleryf2f20.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by sath on 23/01/17.
 */

public class UserResponse {

    ArrayList<Usuario> usuarios;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
