package com.apps.jmm.galleryf2f20.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.apps.jmm.galleryf2f20.pojo.Folder;
import java.util.ArrayList;

/**
 * Created by sath on 3/01/17.
 */

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context, ConstantsDataBase.DATABASE_NAME, null, ConstantsDataBase.DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableFolder(db);
        createTableListMedia(db);
    }


    private void createTableFolder(SQLiteDatabase db){
        String queryCreateTabFolder = "CREATE TABLE " + ConstantsDataBase.TABLE_CARPETA + "( " + ConstantsDataBase.TABLE_CARPETA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsDataBase.TABLE_CARPETA_NOMBRE + " TEXT, " + ConstantsDataBase.TABLE_CARPETA_PATH + " TEXT, " + ConstantsDataBase.TABLE_CARPETA_FOTO + " INTEGER)";
        db.execSQL(queryCreateTabFolder);
    }

    private void createTableListMedia(SQLiteDatabase db){
        String queryCreateTabFolder = "CREATE TABLE " + ConstantsDataBase.TABLE_MEDIA_FOLDER + "( " + ConstantsDataBase.TABLE_MEDIA_FOLDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsDataBase.TABLE_MEDIA_FOLDER_NOMB + " TEXT, " + ConstantsDataBase.TABLE_MEDIA_FOLDER_PATH + " TEXT, " + ConstantsDataBase.TABLE_MEDIA_FOLDER_ICON + " INTEGER)";
        db.execSQL(queryCreateTabFolder);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTableMediaFolder(db);
        dropTableFolder(db);
        onCreate(db);
    }

    private void dropTableMediaFolder(SQLiteDatabase db){
        db.execSQL("DROP TABLE " + ConstantsDataBase.TABLE_MEDIA_FOLDER);
    }
    private void dropTableFolder(SQLiteDatabase db){
        db.execSQL("DROP TABLE " + ConstantsDataBase.TABLE_CARPETA);
    }

    public void deleteAllTableMediaFolder(){
        String query = "DELETE FROM " + ConstantsDataBase.TABLE_MEDIA_FOLDER;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public ArrayList<Folder> getAllFolders() {

        ArrayList<Folder> carpetas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantsDataBase.TABLE_CARPETA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()) {
            Folder folder = new Folder();
            folder.setId(cur.getString(0));
            folder.setNombre(cur.getString(1));
            folder.setPath(cur.getString(2));
            folder.setIcono(cur.getInt(3));
            carpetas.add(folder);
        }

        db.close();

        return carpetas;
    }

    public ArrayList<Folder> getAllMediaFolders() {

        ArrayList<Folder> carpetas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantsDataBase.TABLE_MEDIA_FOLDER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()) {
            Folder folder = new Folder();
            folder.setId(cur.getString(0));
            folder.setNombre(cur.getString(1));
            folder.setPath(cur.getString(2));
            folder.setIcono(cur.getInt(3));
            carpetas.add(folder);
        }

        db.close();

        return carpetas;
    }

    public Folder getFirstFolder(){
        Folder folder = new Folder();

        String query = "SELECT * FROM " + ConstantsDataBase.TABLE_CARPETA + " ORDER BY " + ConstantsDataBase.TABLE_CARPETA_ID + " ASC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()) {
            folder.setId(cur.getString(0));
            folder.setNombre(cur.getString(1));
            folder.setPath(cur.getString(2));
            folder.setIcono(cur.getInt(3));
        }

        db.close();

        return folder;
    }

    public void insertFolder(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantsDataBase.TABLE_CARPETA, null, contentValues);
        db.close();
    }
    public void insertMediaFolder(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantsDataBase.TABLE_MEDIA_FOLDER, null, contentValues);
        db.close();
    }
    public boolean existFolder() {

        boolean rep = true;

        String query = "SELECT COUNT(*) FROM " + ConstantsDataBase.TABLE_CARPETA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()) {
            int i = cur.getInt(0);
            if (i > 0) {
                rep = false;
            }
        }
        return rep;
    }
    public boolean existMediaFolder() {

        boolean rep = true;

        String query = "SELECT COUNT(*) FROM " + ConstantsDataBase.TABLE_MEDIA_FOLDER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()) {
            int i = cur.getInt(0);
            if (i > 0) {
                rep = false;
            }
        }
        return rep;
    }
    public void deleteFolder(int id) {

        String query = "DELETE FROM " + ConstantsDataBase.TABLE_CARPETA + " WHERE " + ConstantsDataBase.TABLE_CARPETA_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }
}