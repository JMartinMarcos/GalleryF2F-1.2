package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.asyncProcess;

import android.os.AsyncTask;

import com.apps.jmm.galleryf2f20.model.MakeModelFolders;

import java.io.IOException;

/**
 * Created by sath on 27/11/17.
 */

public class AsyncFindMediaFolders extends AsyncTask<MakeModelFolders,Void,Void> {

    @Override
    protected Void doInBackground(MakeModelFolders... makeModelFolders) {
        try {
            makeModelFolders[0].loadAllMediaFolderDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
