/*
 * Copyright (c) 2017 Jemshit Iskanderov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jemshit.elitemvp.base_presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.jemshit.elitemvp.view.EliteView;

/**
 * Complete <b>Presenter</b> class, which holds reference to {@link EliteView EliteView} instance.
 * Contains <b>getView()</b>, <b>isViewAttached()</b> helper methods and
 * <b>onDestroy()</b> lifecycle method.
 */
public class ElitePresenter<V extends EliteView> implements EliteCorePresenter<V> {

    protected V view;

    /**
     * Attaches {@link EliteView EliteView} instance to this <b>Presenter</b>
     *
     * @param view instance of {@link EliteView EliteView}
     */
    @UiThread @Override @CallSuper
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * Helper method that returns attached {@link EliteView EliteView} instance if exists.
     * Returns <b>null</b> if no <b>View</b> is attached
     *
     * @return V instance of {@link EliteView EliteView} that is attached to this <b>Presenter</b>
     */
    @UiThread @Nullable @CallSuper
    protected V getView() {
        return view;
    }

    /**
     * Helper method that returns if <b>View</b> is attached to this <b>Presenter</b>.
     */
    @UiThread @CallSuper
    protected boolean isViewAttached() {
        return view != null;
    }

    /**
     * Detaches attached {@link EliteView EliteView} instance from this <b>Presenter</b>
     */
    @UiThread @CallSuper
    @Override public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    /**
     * Should be called when this <b>Presenter</b> instance has to be destroyed.
     * This method internally calls {@link #detachView() detachView()}
     */
    @CallSuper
    public void onDestroy() {
        detachView();
    }

}
