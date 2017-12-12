package com.apps.jmm.galleryf2f20.FullScreenPhotoView;

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
import android.widget.Toast;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.adapter.MyPageAdapter;
import com.apps.jmm.galleryf2f20.FullScreenPhotoView.presenter.FullScreenActivityPresenter;
import com.apps.jmm.galleryf2f20.FullScreenPhotoView.presenter.IFullScreenActivityPresenter;
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
public class FullScreenActivity extends BaseActivity implements IFullScreenActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    ArrayList<Photo> photos;
    int position = 0;

    MyPageAdapter adapterViewPager;

    IFullScreenActivityPresenter iFullScreenActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        photos = bundle.getParcelableArrayList("fotos");
        position = bundle.getInt("position");

        iFullScreenActivityPresenter = new FullScreenActivityPresenter(this, photos, position);

    }

    public void loadViewPger(ArrayList<Photo> photos, int position) {
        adapterViewPager = new MyPageAdapter(getSupportFragmentManager(), photos);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(position);
    }

    public MyPageAdapter buildAdapterViewPagerPhotos(ArrayList<Photo> photos, FragmentManager fm) {
        return new MyPageAdapter(fm, photos);
    }

    @Override
    public Uri getUriForFilePath() {
        return Uri.parse(new File(adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getImagen()).getAbsolutePath());
    }

    public void sharedImg() {

        try {
            Uri uri = getUriForFilePath();
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(uri);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(intent, "Share img"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editImg() {
        solicitarPermisoWrite();
        try {
            Intent editIntent = new Intent(Intent.ACTION_EDIT);
            Uri uri = getUriForFilePath();
            editIntent.setData(uri);
            editIntent.putExtra(Intent.EXTRA_STREAM, uri);
            editIntent.setType("image/*");
            editIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivity(Intent.createChooser(editIntent, null));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error inesperado al editar: " + adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteImg() {

        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(FullScreenActivity.this);
        alertDialog1.setTitle("Borrar:")
                .setMessage("De verdad quieres borrar la imagen?")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            File file = new File(adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getImagen());
                            if (file.exists()) {
                                solicitarPermisoWrite();
                                deletePhoto(file, viewPager.getCurrentItem());
                            } else {
                                Toast.makeText(getApplicationContext(), "Fichero no existe: " + adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), getString(R.string.cancelar) + adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                                Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }

    public void deletePhoto(File file, int imagePos) {
        if (file.delete()) {
            adapterViewPager.notifyChangeInPosition(1);
            adapterViewPager.deleteData(imagePos);
        } else {
            Toast.makeText(getApplicationContext(), "No se pudo borrar: " + adapterViewPager.getPhotoInPosition(viewPager.getCurrentItem()).getNombre(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fullscreen;
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
        iFullScreenActivityPresenter.sharedPhoto();
    }

    @OnClick(R.id.btn_copy)
    public void editButton() {
        iFullScreenActivityPresenter.editPhoto();
    }

    @OnClick(R.id.btn_trush)
    public void deleteImage() {
        iFullScreenActivityPresenter.removePhoto();
    }

}
