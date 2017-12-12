/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apps.jmm.galleryf2f20.bases;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = injectViews();

    }

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment, Activity activity) {
        activity.getFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    private Unbinder injectViews() {
        return ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public abstract int getLayoutId();

}