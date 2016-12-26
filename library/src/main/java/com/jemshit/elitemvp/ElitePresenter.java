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

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

public class ElitePresenter<V extends EliteView> implements EliteCorePresenter<V> {
    private WeakReference<V> viewRef;                           // WeakReference for View
    protected CompositeSubscription subscriptionsAllLifetime;   // Between onCreate-onDestroy
    protected CompositeSubscription subscriptionsAttachLifetime;// Between onAttach-onDetach

    public void onCreate(){
        subscriptionsAllLifetime=new CompositeSubscription();
    };

    @UiThread
    @Override public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        subscriptionsAttachLifetime=new CompositeSubscription();
    }

    @UiThread @Nullable
    protected V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @UiThread
    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @UiThread
    @Override public void detachView() {
        if(subscriptionsAttachLifetime!=null && !subscriptionsAttachLifetime.isUnsubscribed())
            subscriptionsAttachLifetime.unsubscribe();

        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    public void onDestroy(){
        detachView();

        if(subscriptionsAllLifetime!=null && !subscriptionsAllLifetime.isUnsubscribed())
            subscriptionsAllLifetime.unsubscribe();
    };
}
