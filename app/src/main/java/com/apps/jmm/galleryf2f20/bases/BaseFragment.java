package com.apps.jmm.galleryf2f20.bases;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        // This lifecycle method still gets called even if onCreateView returns a null view.
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    protected abstract int getFragmentLayout();

    protected final void addChildFragment(@IdRes int containerViewId, Fragment fragment, Activity activity) {
        activity.getFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

}