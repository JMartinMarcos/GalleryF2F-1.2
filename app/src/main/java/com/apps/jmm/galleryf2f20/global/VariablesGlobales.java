package com.apps.jmm.galleryf2f20.global;

import android.os.Environment;

import com.apps.jmm.galleryf2f20.model.GetAllSds;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by sath on 5/04/17.
 */

public class VariablesGlobales {


    public static final String PATH_RAIZ_INTERNAL = Environment.getExternalStorageDirectory().toString();
    public static final String PATH_INTERNAL_GALL = PATH_RAIZ_INTERNAL + File.separator + Environment.DIRECTORY_DCIM + File.separator + "100ANDRO" + File.separator;
    public static final String PATH_INTERNAL_GALL_7 = PATH_RAIZ_INTERNAL + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator;
    public static final String PATH_WHATSAPP_GALL = Environment.getExternalStorageDirectory() + "/WhatsApp/Media/WhatsApp Images/";
    public static boolean FOLDER_TRASH = false;
    public static String PATH_ARCH = "";
    public static String PATH_GALL = "";
    public static String PATH_RAIZ_EXTERNAL_SD = "";
    public static final String PATH_RAIZ_EXTERNAL_SD_GALL = PATH_RAIZ_EXTERNAL_SD + File.separator + Environment.DIRECTORY_DCIM + File.separator + "100ANDRO" + File.separator;
    public static final String PATH_RAIZ_EXTERNAL_SD_GALL_7 = PATH_RAIZ_EXTERNAL_SD + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator;

/*    private static String externalSD() {
        GetAllSds sd = new GetAllSds();

        ArrayList<String> pathSD = sd.getPaths();
        String SD = null;
        for (String s : pathSD) {
            SD = s;
        }

        if (pathSD.size() > 1) {
            return SD;
        } else {
            return "NoEncontrado";
        }
    }*/
}
