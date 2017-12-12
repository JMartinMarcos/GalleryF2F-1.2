package com.apps.jmm.galleryf2f20.decoration;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Lincoln on 05/04/16.
 */
class SquareLayout extends CardView {

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

/*    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set a square layout.

/*        Random mRandom = new Random();

        int with = MeasureSpec.getSize(widthMeasureSpec);

        int min = with - with / 4;
        int ran = with / 2;
        int height = mRandom.nextInt(ran)+min;*/

        super.onMeasure(widthMeasureSpec,widthMeasureSpec);

/*



        int w = resolveSize(widthMeasureSpec, widthMeasureSpec);
        int h = resolveSize(height, heightMeasureSpec);

        super.onMeasure(w,h);


        setMeasuredDimension(with,with);

        setMeasuredDimension(w, h);

        setMeasuredDimension(widthMeasureSpec, height);
*/


    }



}