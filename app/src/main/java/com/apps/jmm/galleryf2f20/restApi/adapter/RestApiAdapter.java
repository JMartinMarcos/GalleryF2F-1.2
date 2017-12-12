package com.apps.jmm.galleryf2f20.restApi.adapter;

import com.apps.jmm.galleryf2f20.restApi.ConstantesRestApi;
import com.apps.jmm.galleryf2f20.restApi.EndPointApi;
import com.apps.jmm.galleryf2f20.restApi.deserializador.ImageDeserializer;
import com.apps.jmm.galleryf2f20.restApi.deserializador.UsuarioDeserializador;
import com.apps.jmm.galleryf2f20.restApi.model.ImageResponse;
import com.apps.jmm.galleryf2f20.restApi.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sath on 18/01/17.
 */

public class RestApiAdapter {

    public EndPointApi estalecerConnexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointApi.class);
    }

    public EndPointApi estalecerConnexionRestApiInstagramNoGson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndPointApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent (){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ImageResponse.class, new ImageDeserializer());
        return gsonBuilder.create();
    }
    public Gson construyeGsonDeserializadorSearchUser (){
        GsonBuilder gsonBuilder2 = new GsonBuilder();
        gsonBuilder2.registerTypeAdapter(UserResponse.class, new UsuarioDeserializador());
        return gsonBuilder2.create();
    }

    public EndPointApi establecerConexionRestApiHeroku(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPointApi.class);

    }
}
