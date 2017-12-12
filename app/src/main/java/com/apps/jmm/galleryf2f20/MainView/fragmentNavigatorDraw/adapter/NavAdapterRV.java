package com.apps.jmm.galleryf2f20.MainView.fragmentNavigatorDraw.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.jmm.galleryf2f20.MainView.fragmentDialogNewFolder.DialogNewFolder;
import com.apps.jmm.galleryf2f20.MainView.fragmentRecyclerMain.RecyclerFragment;
import com.apps.jmm.galleryf2f20.R;
import com.apps.jmm.galleryf2f20.pojo.Folder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sath on 1/05/17.
 */

public class NavAdapterRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Folder> contentFolders;
    Activity activity;
    Context context;
    ViewPager viewPager;
    private OnItemSelectedListener mListener;
    private Folder folder;
    private List<SwipedState> mItemSwipedStates;

    public NavAdapterRV(ArrayList<Folder> contentFolders, Activity activity, Context context) {
        this.contentFolders = contentFolders;
        this.activity = activity;
        this.context = context;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i <= contentFolders.size(); i++) {
            mItemSwipedStates.add(i, SwipedState.SHOWING_PRIMARY_CONTENT);
        }
    }

    public void addAll(ArrayList<Folder> list) {
        contentFolders.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        contentFolders.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rowView;
        ViewPagerAdapterRecycler adapter;

        switch (viewType)
        {
            case VIEW_TYPES.Normal:
                rowView=(ViewPager) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardview_navigator, parent, false);
                 adapter = new ViewPagerAdapterRecycler();
                ((ViewPager) rowView.findViewById(R.id.viewPagerRecycler)).setAdapter(adapter);
                return new NavViewHolder(rowView);

            case VIEW_TYPES.Header:
                rowView=LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header_main, parent, false);
                return new HeaderHolder(rowView);
            case VIEW_TYPES.Footer:
                rowView=LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_footer, parent, false);
                return new FooterHolder(rowView);
            default:
                rowView=(ViewPager) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardview_navigator, parent, false);
                 adapter = new ViewPagerAdapterRecycler();
                ((ViewPager) rowView.findViewById(R.id.viewPagerRecycler)).setAdapter(adapter);
                return new NavViewHolder(rowView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case VIEW_TYPES.Header:
                break;
            case VIEW_TYPES.Footer:
                break;
            case VIEW_TYPES.Normal:
                onBindNavViewHolder(holder,position);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPES.Header;
        } else if (position == contentFolders.size() - 1) {
            return VIEW_TYPES.Footer;
        }else return VIEW_TYPES.Normal;
    }


    public void onBindNavViewHolder(RecyclerView.ViewHolder holder, final int position) {

        NavViewHolder navViewHolder = (NavViewHolder)holder;
        folder = contentFolders.get(position);
        viewPager = navViewHolder.viewPagerRecycler;

        Glide.with(activity)
                .load(folder.getIcono())
                .into(navViewHolder.imgNav);

        navViewHolder.textNav.setText(folder.getNombre());
        navViewHolder.viewPagerRecycler.setCurrentItem(1);

        navViewHolder.viewPagerRecycler.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int previousPagePosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == previousPagePosition) {

                    return;
                }

                switch (position) {
                    case 0:

                        mItemSwipedStates.set(position, SwipedState.SHOWING_SECONDARY_CONTENT);

                        break;
                    case 1:
                        mItemSwipedStates.set(position, SwipedState.SHOWING_PRIMARY_CONTENT);

                        break;

                }
                previousPagePosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return contentFolders.size();
    }

    public void setFragment(String photoPath) {

        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, new RecyclerFragment().newInstance(photoPath, "IMG"))
                .commit();
    }

    public void setOnItemClickLister(OnItemSelectedListener mListener) {
        this.mListener = mListener;
    }

    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT,
        SHOWING_SECONDARY_CONTENT
    }


    public interface OnItemSelectedListener {
        void onItemSelected(View v, Folder folder);

        void onItemDeleteSelected(View v, String idFolder);

    }

    public class NavViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_nav)
        ImageView imgNav;

        @BindView(R.id.text_nav)
        TextView textNav;

        @BindView(R.id.btnTrash)
        ImageButton btnTrash;

        @BindView(R.id.btn_back)
        ImageButton btnBack;

        @BindView(R.id.viewPagerRecycler)
        ViewPager viewPagerRecycler;

        @BindView(R.id.cv_navigator1)
        RelativeLayout cv_navigator1;

        public NavViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(1);
                }
            });

            cv_navigator1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, contentFolders.get(getLayoutPosition()));
                }
            });

            btnTrash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mListener.onItemDeleteSelected(view,contentFolders.get(getLayoutPosition()).getId());
                    contentFolders.remove(getLayoutPosition());
                    notifyItemRemoved(getLayoutPosition());
                    notifyDataSetChanged();
                }
            });
        }

    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }

    }

    public class FooterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewFooter)
        RelativeLayout rl_newFolder;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
       //     rl_newFolder=itemView.findViewById(R.id.cardViewFooter);
/*            rl_newFolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogNewFolder dialogNewFolder = new DialogNewFolder();
                    dialogNewFolder.show(activity.getFragmentManager(),"NewFileDialog");
                }
            });*/
        }

        @OnClick(R.id.cardViewFooter)
        public void trigerDialogNewFolder(){
            DialogNewFolder dialogNewFolder = new DialogNewFolder();
            dialogNewFolder.show(activity.getFragmentManager(),"NewFileDialog");
        }
    }

    private class VIEW_TYPES {
        public static final int Header = 1;
        public static final int Normal = 2;
        public static final int Footer = 3;
    }

}
