package com.apps.jmm.galleryf2f20.restApi.deserializador;

import com.apps.jmm.galleryf2f20.pojo.Usuario;
import com.apps.jmm.galleryf2f20.restApi.JsonKeys;
import com.apps.jmm.galleryf2f20.restApi.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by sath on 23/01/17.
 */

public class UsuarioDeserializador implements JsonDeserializer<UserResponse> {
    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserResponse usuarioResp = gson.fromJson(json,UserResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        usuarioResp.setUsuarios(deserializarUsuarioDeJson(jsonArray));
        return usuarioResp;
    }

    private ArrayList<Usuario> deserializarUsuarioDeJson(JsonArray jsonArray) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

       // JsonObject userResponseDataObject = jsonArray.get(0).getAsJsonObject();
        JsonObject userJson = jsonArray.get(0).getAsJsonObject();


        //JsonObject userJson = userResponseDataObject.getAsJsonObject(JsonKeys.USER_NAME);
        String id = userJson.get(JsonKeys.USER_ID).getAsString();
        String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
        String fotoUsuario = userJson.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

        Usuario userInsta = new Usuario();
        userInsta.setId(id);
        userInsta.setFullName(nombreCompleto);
        userInsta.setPicture(fotoUsuario);
        usuarios.add(userInsta);
        return usuarios;
    }
}
