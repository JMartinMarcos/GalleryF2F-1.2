package com.apps.jmm.galleryf2f20.FullScreenVideo.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class VideoFragment extends Fragment {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
/*    private final MediaController.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }

            //       deslizarImagen(event);

            return false;
        }
    };*/


    ViewPager viewPager;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.

            viewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        }
    };
    RelativeLayout relativeLayout;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };
    String video;
    int position = 0;
    int videoPosition = 0;
    MediaController mediaController;

    @BindView(R.id.videoViewFullScreen)
    VideoView videoGall;
    Unbinder unbinder;
    private boolean mVisible = false;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hideFull();
        }
    };

    private MediaPlayer.OnPreparedListener videoViewListener =
            new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
/*
* Se indica al reproductor multimedia que el vídeo
* se reproducirá en un loop (on repeat).
*/
//                    mediaPlayer.setLooping(true);
//                    videoGall.setZOrderOnTop(false);
                    videoGall.start();

                    if (videoPosition == 0) {
/*
* Si tenemos una posición en savedInstanceState,
* el vídeo debería comenzar desde aquí.
*/
                        videoGall.start();
                    } else {
/*
* Si venimos de un Activity "resumed",
* la reproducción del vídeo será pausada.
*/
                        videoGall.pause();
                    }
                }
            };

    public VideoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(Photo photo, int position) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString("FOTO", photo.getImagen());
        args.putInt("POSITION", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            video = getArguments().getString("FOTO");
            position = getArguments().getInt("POSITION");
            viewPager = getActivity().findViewById(R.id.viewpager);
            relativeLayout = getActivity().findViewById(R.id.fullscreen_content_controls);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected int getFragmentLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        delayedHide(100);

        videoPosition++;

        setUpVideoView(video);
    }

    public VideoView getVideoGall() {
        return videoGall;
    }


    private void setUpVideoView(String uriPath) {
        Uri uri = Uri.parse(uriPath);
// Se crean los controles multimedia.
//        mediaController = new MediaController(getContext());

        mediaController = new MediaController(getActivity()) {
            @Override
            public void show(int timeout) {
                //we NEVER want to pass anything BUT 0, because controller always has to be visible.
                //so ignore the passed argument, and pass 0
                super.show(0);
                showFull();
            }

            @Override
            public void hide() {
                super.hide();
                hideFull();
            }
        };


// Asigna los controles multimedia a la VideoView.
        videoGall.setMediaController(mediaController);
        try {
// Asigna la URI del vídeo que será reproducido a la vista.
            videoGall.setVideoURI(uri);
// Se asigna el foco a la VideoView.
            videoGall.requestFocus();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
/*
* Se asigna un listener que nos informa cuando el vídeo
* está listo para ser reproducido.
*/
        videoGall.setOnPreparedListener(videoViewListener);
    }

    private void toggle() {
        if (mVisible) {
            hideFull();
        } else {
            showFull();
        }
    }

    private void hideFull() {
        // Hide UI first
        getActivity().findViewById(R.id.fullscreen_content_controls).setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void showFull() {
        // Show the system bar
        getActivity().findViewById(R.id.viewpager).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onDestroyView() {
        // This lifecycle method still gets called even if onCreateView returns a null view.
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }


/*    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        toggle();

        return false;
    }*/
}