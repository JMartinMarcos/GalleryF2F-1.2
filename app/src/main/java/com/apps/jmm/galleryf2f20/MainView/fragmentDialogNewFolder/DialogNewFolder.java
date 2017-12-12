package com.apps.jmm.galleryf2f20.MainView.fragmentDialogNewFolder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.apps.jmm.galleryf2f20.model.MakeModelFolders;
import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sath on 21/11/17.
 */

public class DialogNewFolder extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        MakeModelFolders makeModelFolders = new MakeModelFolders(getActivity());
        final ArrayList<String> dialogList = new ArrayList<>();

        for (Folder data : makeModelFolders.getDataBaseMediaFolder()) dialogList.add(data.getId() + " - " + data.getNombre());
        CharSequence[] listFolders = dialogList.toArray(new CharSequence[dialogList.size()]);

        builder.setTitle("ELIGE CARPETA")
                .setItems(listFolders, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), dialogList.get(i), Toast.LENGTH_LONG).show();
                    }
                });

        return builder.create();
    }
}
