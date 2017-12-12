package com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Photo;
import com.apps.jmm.galleryf2f20.utils.GlideAppSwitch;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sath on 17/03/17.
 */

public class GallAdapter extends RecyclerView.Adapter<GallAdapter.GallViewHolder> {

    ArrayList<Photo> photos;
    Activity activity;
    private Random mRandom = new Random();
    private OnItemSelectedListener mListener;


    public GallAdapter(ArrayList<Photo> photos, Activity activity) {
        this.photos = photos;
        this.activity = activity;
    }

    @Override
    public GallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new GallViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GallViewHolder holder, int position) {
        final Photo photo = photos.get(position);
        final int indexPhoto = position;
        final String url = photo.getImagen();

/*
        holder.imageCard.getLayoutParams().height = getRandomIntInRange(300,200);
*/

        GlideAppSwitch glideAppSwitch = new GlideAppSwitch(activity, url, holder.imageCard);
        glideAppSwitch.GlideAppLoad();
    }


    // Custom method to get a random number between a range
    protected int getRandomIntInRange(int max, int min) {
        return mRandom.nextInt((max - min) + min) + min;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void updateData(ArrayList<Photo> lista) {
        photos.clear();
        photos.addAll(lista);
        notifyDataSetChanged();
    }

    public void setOnItemClickLister(OnItemSelectedListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(View v, int position);
    }

    public class GallViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageCard)
        ImageView imageCard;

        public GallViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());
                }
            });
        }
    }

}
