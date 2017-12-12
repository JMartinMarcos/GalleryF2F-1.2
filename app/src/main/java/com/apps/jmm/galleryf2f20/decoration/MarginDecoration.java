package com.apps.jmm.galleryf2f20.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apps.jmm.galleryf2f20.R;

/**
 * Created by sath on 24/05/17.
 */

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public MarginDecoration(Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }

    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin, margin, margin, margin);
    }
}
