package com.apps.jmm.galleryf2f20.model;

import android.content.ContentValues;
import android.content.Context;

import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Folder;
import com.apps.jmm.galleryf2f20.utils.MultimediaFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by sath on 1/05/17.
 */

public class MakeModelFolders {

    private Context context;
    HashSet<String> pathList = new HashSet<>();

    public MakeModelFolders(Context context) {
        this.context = context;
    }

    public ArrayList<Folder> getDataBaseFolders() {
        DataBase db = new DataBase(context);
        if (db.existFolder()) {
            // initialLoadFolders(db);
            loadInitialFolders();
        }
        return db.getAllFolders();
    }

    public ArrayList<Folder> getDataBaseMediaFolder() {
        DataBase db = new DataBase(context);
        return db.getAllMediaFolders();
    }

/*    private void initialLoadFolders(DataBase db) {
        GetAllSds sds = new GetAllSds(context);
        ArrayList<Folder> folders = sds.listSds();
        int i=0;
        int icon = R.drawable.folder_symbol;

        for(Folder carp:folders){

            String prefix = "";

            if (i>0) icon = R.drawable.folder_symbol_violet;

            i++;

            findRootPathSD(carp.getPath(),prefix,icon,db);
        }
    }*/

    public ArrayList<Folder> findAllMediaFolders() throws IOException {

        GetAllSds sds = new GetAllSds(context);
        ArrayList<Folder> rootSds = sds.listSds();
        ArrayList<Folder> folders = new ArrayList<>();

        int i = 0;
        int icon = R.drawable.folder_symbol;

        for (Folder carp : rootSds) {
            String prefix = carp.getNombre() + ": ";
            if (i > 0) icon = R.drawable.folder_symbol_violet;
            i++;
            int j = 4;

            HashSet<String> folderListForSdUnit = findRootPathSD(carp.getPath());
            for (String mediaPath : folderListForSdUnit) {
                File file = new File(mediaPath);
                Folder multimediaFolderFound = new Folder();
                multimediaFolderFound.setIcono(icon);
                multimediaFolderFound.setNombre(file.getName());
                multimediaFolderFound.setPath(mediaPath);
                multimediaFolderFound.setId(prefix);
                folders.add(multimediaFolderFound);
                j++;
            }
        }
        return folders;
    }

    public void loadAllFolderDB() throws IOException {
        DataBase db = new DataBase(context);
        //TODO: añadir delete all a la tabla Folder
        ArrayList<Folder> folders = findAllMediaFolders();
        for (Folder folder : folders) {
            loadPathsToDB(folder.getPath(), folder.getId(),folder.getNombre(), folder.getIcono(), db);
        }
    }

    public void loadAllMediaFolderDB() throws IOException {
        DataBase db = new DataBase(context);
        if (db.existMediaFolder()) db.deleteAllTableMediaFolder();

        ArrayList<Folder> folders = findAllMediaFolders();

        for (Folder folder : folders) {
            loadMediaFolderToDB(folder.getPath(), folder.getId(),folder.getNombre(), folder.getIcono(), db);
        }
    }

    private void loadInitialFolders(){
        DataBase db = new DataBase(context);
        loadPathsToDB("whatsapp", "", "whatsapp",R.drawable.whatsapp_50, db);
        loadPathsToDB("Instagram", "","Instagram", R.drawable.instagram_48, db);

    }

    public void insertFolders(DataBase db, ContentValues values) {
        db.insertFolder(values);
    }

    public void insertMediaFolders(DataBase db, ContentValues values) {
        db.insertMediaFolder(values);
    }


    public void removeFolder(DataBase db, int id) {
        db.deleteFolder(id);
    }


    public HashSet<String> findRootPathSD(String urlBase) {

        File path = new File(urlBase);
        boolean loadPath = true;

        for (File h : path.listFiles(new MultimediaFilter())) {
            if (h.isDirectory()) {
                    findRootPathSD(h.getAbsolutePath());
            } else {
                if (loadPath) {
                    pathList.add(path.getAbsolutePath());
                    //loadPathsToDB(path, prefix,icon, db);
                    loadPath = false;
                }
            }
        }
        return pathList;
    }


    public void loadPathsToDB(String url, String prefix,String name, int icon, DataBase db) {

        ContentValues values = new ContentValues();
        values.put(ConstantsDataBase.TABLE_CARPETA_NOMBRE, prefix + name);
        values.put(ConstantsDataBase.TABLE_CARPETA_PATH, url);
        values.put(ConstantsDataBase.TABLE_CARPETA_FOTO, icon);
        insertFolders(db, values);
    }

    public void loadMediaFolderToDB(String url, String prefix,String name, int icon, DataBase db) {

        ContentValues values = new ContentValues();
        values.put(ConstantsDataBase.TABLE_MEDIA_FOLDER_NOMB, prefix + name);
        values.put(ConstantsDataBase.TABLE_MEDIA_FOLDER_PATH, url);
        values.put(ConstantsDataBase.TABLE_MEDIA_FOLDER_ICON, icon);
        insertMediaFolders(db, values);
    }
}
