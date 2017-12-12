package com.apps.jmm.galleryf2f20.restApi.deserializador;

import com.apps.jmm.galleryf2f20.pojo.Image;
import com.apps.jmm.galleryf2f20.pojo.Photo;
import com.apps.jmm.galleryf2f20.restApi.JsonKeys;
import com.apps.jmm.galleryf2f20.restApi.model.ImageResponse;
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
 * Created by sath on 19/01/17.
 */

public class ImageDeserializer implements JsonDeserializer<ImageResponse> {
    @Override
    public ImageResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ImageResponse imageResponse = gson.fromJson(json,ImageResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        imageResponse.setMascotas(deserializeImageToJson(jsonArray));
        return imageResponse;
    }

    private ArrayList<Image> deserializeImageToJson(JsonArray imageResponseData){

        ArrayList<Image> images = new ArrayList<>();
        for (int i=0;i < imageResponseData.size(); i++){
            JsonObject imageResponseDataObject = imageResponseData.get(i).getAsJsonObject();

            String id_media = imageResponseDataObject.get(JsonKeys.ID_MEDIA).getAsString();

            JsonObject userJson = imageResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject urlFotoJson = imageResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject standarResolutionJson = urlFotoJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);

            String urlFoto = standarResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = imageResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            //int numLikes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();
            String numLikes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsString();

            Image imagesInstagram = new Image();
            imagesInstagram.setId(id);
            imagesInstagram.setName(nombreCompleto);
            imagesInstagram.setImage(urlFoto);
            imagesInstagram.setRating(numLikes);
            imagesInstagram.setId_medeia(id_media);

            images.add(imagesInstagram);
        }
        return images
                ;
    }
}
