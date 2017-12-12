package com.apps.jmm.galleryf2f20.FullScreenPhotoView.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Photo;
import com.apps.jmm.galleryf2f20.utils.GlideAppSwitch;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FotoFragment extends Fragment {

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
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.

            getActivity().findViewById(R.id.viewpager).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            getActivity().findViewById(R.id.fullscreen_content_controls).setVisibility(View.VISIBLE);
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final PhotoView.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }

            //       deslizarImagen(event);

            return false;
        }
    };
    String foto;
    int position = 0;

    @BindView(R.id.photo_view1)
    ImageView fotoGall;

    private boolean mVisible = false;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    Unbinder unbinder;


    public FotoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FotoFragment newInstance(Photo foto, int position) {
        FotoFragment fragment = new FotoFragment();
        Bundle args = new Bundle();
        args.putString("FOTO", foto.getImagen());
        args.putInt("POSITION", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            foto = getArguments().getString("FOTO");
            position = getArguments().getInt("POSITION");
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
        return R.layout.fragment_foto;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        delayedHide(100);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getActivity().getWindow().setExitTransition(fade);
            getActivity().getWindow().setReturnTransition(fade);
        }
        position++;

        GlideAppSwitch glideAppSwitch = new GlideAppSwitch(getActivity(),foto,fotoGall);
        glideAppSwitch.GlideAppLoad();

        fotoGall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();

            }
        });
    }

    public ImageView getFotoGall() {
        return fotoGall;
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        getActivity().findViewById(R.id.fullscreen_content_controls).setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
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
}
