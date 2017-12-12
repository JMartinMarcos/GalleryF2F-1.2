package com.apps.jmm.galleryf2f20.FullScreenVideo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.apps.jmm.galleryf2f20.FullScreenVideo.adapter.VideoPageAdapter;
import com.apps.jmm.galleryf2f20.FullScreenVideo.presenter.FullScreenVideoPresenter;
import com.apps.jmm.galleryf2f20.FullScreenVideo.presenter.IFullScreenVideoPresenter;
import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.bases.BaseActivity;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivityVideo extends BaseActivity implements IFullScreenActivityVideo {


    @BindView(R.id.viewpager)
    ViewPager viewPager;

    ArrayList<Photo> fotos;
    int position = 0;

    VideoPageAdapter adapterVideoView;

    IFullScreenVideoPresenter iFullScreenVideoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        fotos = bundle.getParcelableArrayList("fotos");
        position = bundle.getInt("position");

        iFullScreenVideoPresenter = new FullScreenVideoPresenter(this, fotos, position);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fullscreen;
    }

    public void loadViewPger(ArrayList<Photo> photos, int position) {
        adapterVideoView = new VideoPageAdapter(getSupportFragmentManager(), photos);
        viewPager.setAdapter(adapterVideoView);
        viewPager.setCurrentItem(position);
    }

    @Override
    public VideoPageAdapter buildAdapterViewPagerVideos(ArrayList<Photo> photos, FragmentManager fm) {
        return new VideoPageAdapter(fm, photos);
    }

    public Uri getUriForFilePath() {
        return Uri.parse(new File(adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getImagen()).getAbsolutePath());
    }


    public void sharedVid() {

        try {

            Intent localIntent = new Intent(Intent.ACTION_SEND);

            Uri localUri = getUriForFilePath();

            String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(localUri.toString()));
            localIntent.setType(str);
            localIntent.setAction(Intent.ACTION_SEND);

            if (str == null) {
                str = "*/*";
            }

            localIntent.setType(str);
            localIntent.putExtra(Intent.EXTRA_STREAM, localUri);

            startActivity(Intent.createChooser(localIntent, "Where you want to share?"));


/*            Uri uri = getUriForFilePath();
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(uri);
            intent.setType("image*//*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Share img"));*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editVid() {
        solicitarPermisoWrite();
        try {
            Intent editIntent = new Intent(Intent.ACTION_EDIT);
            Uri uri = getUriForFilePath();
            editIntent.setData(uri);
            editIntent.putExtra(Intent.EXTRA_STREAM, uri);


            String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
            editIntent.setType(str);
            editIntent.setAction(Intent.ACTION_EDIT);

            if (str == null) {
                str = "*/*";
            }

            editIntent.setType(str);
            editIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivity(Intent.createChooser(editIntent, null));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error inesperado al editar: " + adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteVid() {

        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(FullscreenActivityVideo.this);
        alertDialog1.setTitle("Borrar:")
                .setMessage("De verdad quieres borrar la imagen?")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            File file = new File(adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getImagen());
                            if (file.exists()) {
                                solicitarPermisoWrite();
                                deleteVid(file, viewPager.getCurrentItem());
                            } else {
                                Toast.makeText(getApplicationContext(), "Fichero no existe: " + adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), getString(R.string.cancelar) + adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                                Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }

    public void deleteVid(File file, int imagePos) {
        if (file.delete()) {
            adapterVideoView.notifyChangeInPosition(1);
            adapterVideoView.deleteData(imagePos);
        } else {
            Toast.makeText(getApplicationContext(), "No se pudo borrar: " + adapterVideoView.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void solicitarPermisoWrite() {
        final int PERMISO_WRITE = 2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_WRITE);
                SystemClock.sleep(3000);
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_WRITE);
                    SystemClock.sleep(3000);
                }
            }
        }
    }


    @OnClick(R.id.btn_shared)
    public void sharedButton() {
        iFullScreenVideoPresenter.sharedVideo();

    }

    @OnClick(R.id.btn_copy)
    public void editButton() {
        iFullScreenVideoPresenter.editVideo();
    }

    @OnClick(R.id.btn_trush)
    public void deleteButton() {
        iFullScreenVideoPresenter.removeVideo();
    }


}
