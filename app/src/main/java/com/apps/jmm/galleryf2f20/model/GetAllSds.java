package com.apps.jmm.galleryf2f20.model;

import android.content.Context;
import android.os.Environment;

import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Folder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by sath on 20/04/17.
 */

public class GetAllSds {
    Context context;

    public GetAllSds(Context context) {
        this.context = context;
    }

    public ArrayList<Folder> listSds() throws IOException {
        ArrayList<String> paths = getPaths();
        return getFoldersSd(paths);
    }

    public ArrayList<String> getPaths() throws IOException {

        ArrayList<String> tmp = new ArrayList<>();

        HashSet<String> externalMounts = getExternalMounts();
        tmp.add(Environment.getExternalStorageDirectory().getAbsolutePath());
        for (String sd: externalMounts){
            String file = "/storage/";
            File dir = new File(sd);
            File path = new File(file+dir.getName());
            tmp.add(path.getAbsolutePath());

        }
 //       tmp.addAll(externalMounts);
        return tmp;
    }

    public static HashSet<String> getExternalMounts() {
        final HashSet<String> out = new HashSet<String>();
        String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String s = "";
        try {
            final Process process = new ProcessBuilder().command("mount")
                    .redirectErrorStream(true).start();
            process.waitFor();
            final InputStream is = process.getInputStream();
            final byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                s = s + new String(buffer);
            }
            is.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // parse output
        final String[] lines = s.split("\n");
        for (String line : lines) {
            if (!line.toLowerCase(Locale.US).contains("asec")) {
                if (line.matches(reg)) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        if (part.startsWith("/"))
                            if (!part.toLowerCase(Locale.US).contains("vold"))
                                out.add(part);
                    }
                }
            }
        }
        return out;
    }

    public static String getExternalSdCardPath() {
        String path = null;

        File sdCardFile = null;
        List<String> sdCardPossiblePath = Arrays.asList("external_sd", "ext_sd", "external", "extSdCard");

        for (String sdPath : sdCardPossiblePath) {
            File file = new File("/mnt/", sdPath);

            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();

                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                File testWritable = new File(path, "test_" + timeStamp);

                if (testWritable.mkdirs()) {
                    testWritable.delete();
                }
                else {
                    path = null;
                }
            }
        }

        if (path != null) {
            sdCardFile = new File(path);
        }
        else {
            sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        }

        return sdCardFile.getAbsolutePath();
    }


    public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
        if (i >= minValueInclusive && i <= maxValueInclusive)
            return true;
        else
            return false;
    }

    public ArrayList<Folder> getFoldersSd(ArrayList<String> tmp) {

        ArrayList<Folder> folders = new ArrayList<>();
        int icon = R.drawable.hdd_graphite_server_b_256;
        int i = 0;

        for (String sd : tmp) {
//            if (i > 0) icon = R.drawable.hdd_usb_256;
            File file = new File(sd);
            Folder extSD = new Folder();
            extSD.setIcono(icon);
            extSD.setPath(sd);
            extSD.setNombre(file.getName());
            folders.add(extSD);
            i++;
        }

        return folders; // Regresamos el Array de almacenamientos
    }
}
