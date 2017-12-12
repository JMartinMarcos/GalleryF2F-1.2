package com.apps.jmm.galleryf2f20.restApi;

import com.apps.jmm.galleryf2f20.restApi.model.LikeResponse;
import com.apps.jmm.galleryf2f20.restApi.model.UserResponse;
import com.apps.jmm.galleryf2f20.restApi.model.UsuarioResponsePost;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sath on 18/01/17.
 */

public interface EndPointApi {

   @DELETE(ConstantesRestApi.URL_POST_LIKE_MEDIA)
   Call<LikeResponse> desacerLikeMedia(@Path("media-id") String idMedia);

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_POST_NOTIFICA_LIKE)
    Call<UsuarioResponsePost> notificaLikeMedia(@Field("token") String token, @Field("id") String id, @Field("idNotif") String idNotif);

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_POST_LIKE_MEDIA)
    Call<LikeResponse> darLikeMedia(@Field("access_token") String token, @Path("media-id") String idMedia);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponsePost> registrarTokenID(@Field("token") String token, @Field("id") String idInstagram);

    @GET(ConstantesRestApi.URL_GET_USER_BY_NAME)
    Call<UserResponse> getIdUser(@Query("q") String nombreUser);

/*
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_BY_ID_USER)
    Call<PetResponse> getRecentMediaByIdUser(@Path("user") String idUser);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetResponse> getRecentMedia();*/


}
