package com.apps.jmm.galleryf2f20.restApi.model;

/**
 * Created by sath on 3/02/17.
 */

public class UsuarioResponsePost {
    private String id_auto;
    private String id_dispositivo;
    private String id_usuario_instagram;
   // private String id_foto_instagram;

    public UsuarioResponsePost() {
    }

    public UsuarioResponsePost(String id, String id_dispositivo, String id_usuario_instagram, String id_foto_instagram) {
        this.id_auto = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    //    this.id_foto_instagram = id_foto_instagram;
    }
    public String getId() {
        return id_auto;
    }

    public void setId(String id) {
        this.id_auto = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
  /*  public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    } */
}
