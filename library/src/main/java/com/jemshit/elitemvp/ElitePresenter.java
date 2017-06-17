/*
 * Copyright (c) 2016 Jemshit Iskanderov.
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

package com.jemshit.elitemvp;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * Complete <b>presenter</b> class, which handles how to hold reference of {@link EliteView EliteView} instance with additional helper methods.
 * Also contains <b>onCreate()</b> and <b>onDestroy()</b> methods for lifecycle
 *
 * @param <V> is instance of {@link EliteView EliteView}, which this Presenter holds reference to
 */
public class ElitePresenter<V extends EliteView> implements EliteCorePresenter<V> {

    private V view;

    /**
     * Should be called from {@link ElitePresenter ElitePresenter} constructor when it is created.
     */
    public void onCreate() {
    }

    /**
     * Attach {@link EliteView EliteView} instance to this <b>presenter</b>
     *
     * @param view is instance of {@link EliteView EliteView}
     */
    @UiThread @CallSuper
    @Override public void attachView(V view) {
        this.view = view;
    }

    /**
     * Helper method returns {@link EliteView EliteView} instance which this <b>presenter</b> holds
     * reference to if exists. If does not exist, returns <b>null</b>
     *
     * @return V instance of {@link EliteView EliteView}
     */
    @UiThread @Nullable
    protected V getView() {
        return view;
    }

    /**
     * Helper method that returns if this <b>presenter</b> holds reference to {@link EliteView EliteView} instance.
     *
     * @return boolean if {@link EliteView EliteView} instance exists
     */
    @UiThread
    protected boolean isViewAttached() {
        return view != null;
    }

    /**
     * Clears reference to {@link EliteView EliteView} instance if exists.
     */
    @UiThread @CallSuper
    @Override public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    /**
     * Should be called when this presenter instance has to be destroyed. This method internally calls
     * {@link #detachView() detachView()}
     */
    @CallSuper
    public void onDestroy() {
        detachView();
    }

}
