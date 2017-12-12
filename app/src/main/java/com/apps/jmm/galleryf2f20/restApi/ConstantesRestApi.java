package com.apps.jmm.galleryf2f20.restApi;

/**
 * Created by sath on 18/01/17.
 */

public class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4476764324.283fbbc.658bc8ff56da442a90fe539764c3bde9";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    //public static final String KEY_ACCESS_TOKEN_AND = "&access_token=";

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_USER_SHEARCH ="users/search";
    public static final String KEY_GET_USER_MEDIA_RECENT = "users/{user}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static String UsuarioInsta = "masscotascursera";
    public static String IdUser = "4476764324";
    public static String IdMedia = "";

    public static final String URL_GET_USER_BY_NAME = KEY_GET_USER_SHEARCH + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_RECENT_MEDIA_BY_ID_USER = KEY_GET_USER_MEDIA_RECENT + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // https://api.instagram.com/v1/users/search?q=masscotascursera&access_token=4476764324.283fbbc.658bc8ff56da442a90fe539764c3bde9
    //https://api.instagram.com/v1/users/4476764324/media/recent/?access_token=4476764324.283fbbc.658bc8ff56da442a90fe539764c3bde9

    public static final String ROOT_URL_HEROKU = "https://infinite-beach-28848.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";
    //https://infinite-beach-28848.herokuapp.com/token_device/

    public static final String URL_POST_LIKE_MEDIA = "media/{media-id}/likes" + KEY_ACCESS_TOKEN + ACCESS_TOKEN ;
    //https://api.instagram.com/v1/media/{media-id}/likes
    public static final String URL_POST_NOTIFICA_LIKE = "notifica-like/";


    //https://api.instagram.com/v1/media/1429953050373787175_4476764324/likes?access_token=4476764324.283fbbc.658bc8ff56da442a90fe539764c3bde9
    //https://api.instagram.com/v1/media/{media-id}/likes?access_token=ACCESS-TOKEN

}
