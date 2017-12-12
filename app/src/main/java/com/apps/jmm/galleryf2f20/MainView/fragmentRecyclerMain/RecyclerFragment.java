package com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.apps.jmm.galleryf2f20.FullScreenPhotoView.FullScreenActivity;
import com.apps.jmm.galleryf2f20.FullScreenVideo.FullscreenActivityVideo;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter.GallAdapter;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.presenter.IRecyclerPresenter;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.presenter.RecyclerPresenter;
import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.bases.BaseFragment;
import com.apps.jmm.galleryf2f20.global.VariablesGlobales;
import com.apps.jmm.galleryf2f20.model.PhotographLister;
import com.apps.jmm.galleryf2f20.pojo.Photo;

import java.util.ArrayList;

import butterknife.BindView;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends BaseFragment implements IRecycler {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int VISIBLE = 0 ;

    GallAdapter gallAdapter;
    @BindView(R.id.rvGall)
    RecyclerView galleryPhoto;
    @BindView(R.id.textSinItem)
    TextView textSinItem;
    private IRecyclerPresenter presenter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String viewTag;
    public static final String TAG_PORTRAIT = "V11-portrait";
    public static final String TAG_LANDSCAPE = "V11-landscape";

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mParam1.isEmpty())
            mParam1 = VariablesGlobales.PATH_RAIZ_INTERNAL;
        presenter = new RecyclerPresenter(getActivity(), this, new PhotographLister(mParam1,mParam2));
    }

    @Override
    public void configureLayout() {
/*
        galleryPhoto.addItemDecoration(new MarginDecoration(getActivity()));
*/
        galleryPhoto.setHasFixedSize(true);

        final int spanCount;

        int rotation = getActivity().getResources().getConfiguration().orientation;

        switch (rotation) {
            case ORIENTATION_PORTRAIT:
                spanCount = 2;
                break;
            case ORIENTATION_LANDSCAPE:
                spanCount = 3;
                break;
            default:
                spanCount = 2;
                break;
           }


        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_gallery_main, null);
        viewTag = (String) view.getTag();

            galleryPhoto.setLayoutManager(new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL));


       // galleryPhoto.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @SuppressLint("WrongConstant")
    @Override
    public GallAdapter createAdapter(ArrayList<Photo> photos) {
        gallAdapter = new GallAdapter(photos, getActivity());
        if(photos.size()== 0)
            textSinItem.setVisibility(VISIBLE);
        return gallAdapter;
    }

    @Override
    public void AdapterInitialize(GallAdapter gAdapter) {
        configureLayout();
        galleryPhoto.setAdapter(gAdapter);
    }

    @Override
    public void AdapterUpdate(GallAdapter gAdaptador, ArrayList<Photo> listPhoto) {
        gallAdapter.updateData(listPhoto);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.reloadRecycler();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_recycler;
    }

}


