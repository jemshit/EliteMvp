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

package com.jemshit.elitemvp.null_view_presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.jemshit.elitemvp.rx_presenter.EliteRx1Presenter;
import com.jemshit.elitemvp.view.EliteView;

/**
 * <b>Presenter</b> class that follows <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">Null
 * Object Pattern</a>. When <b>View</b> is requested with <b>getView()</b>, it will either return
 * attached <b>View</b> or <b>No Operation Empty View</b> with empty methods is returned.
 * This way no need for call <b>isViewAttached()</b> anymore. This <b>Presenter</b> also stores two
 * <b>CompositeSubscription</b> objects for RxJava 1 <b>Subscriptions</b>
 */
public class EliteNullViewRx1Presenter<V extends EliteView> extends EliteRx1Presenter<V> {

    private V nullView;
    private boolean isViewAttachedOnce = false;

    /**
     * Gets type of <b>View</b> and creates <b>No Operation Empty View</b>
     *
     * @param viewType Type (Class) of <b>View</b>
     */
    public EliteNullViewRx1Presenter(Class<V> viewType) {
        nullView = NullObjectUtility.getNoOpObject(viewType);
    }

    @Override
    public void attachView(V view) {
        super.attachView(view);
        isViewAttachedOnce = true;
    }

    /**
     * Returns attached <b>View</b> if exists, <b>No Operation Empty View</b> otherwise
     */
    @Nullable @Override @CallSuper
    protected V getView() {
        if (!isViewAttachedOnce)
            throw new RuntimeException("No View has ever been attached to this Presenter.");
        else if (view == null)
            return nullView;
        else
            return super.getView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nullView = null;
    }
}
