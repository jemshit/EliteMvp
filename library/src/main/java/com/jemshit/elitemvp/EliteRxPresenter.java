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

package com.jemshit.elitemvp;

import android.support.annotation.UiThread;

import rx.subscriptions.CompositeSubscription;

/**
 * <b>Presenter</b> class extends {@link ElitePresenter} with additional RxJava <b>CompositeSubscription</b>
 * instances and codes. This class holds 2 <b>CompositeSubscription</b> instances: one instance lives between
 * {@link #onCreate()} and {@link #onDestroy()}; second instance lives between {@link #attachView(EliteView view) attachView()} and
 * {@link #detachView()};
 */
public class EliteRxPresenter<V extends EliteView> extends ElitePresenter<V> {

    protected CompositeSubscription subscriptionsAllLifetime;
    protected CompositeSubscription subscriptionsAttachLifetime;// Between onAttach-onDetach

    /**
     * Creates <b>CompositeSubscription</b> that lives between {@link #onCreate()} and {@link #onDestroy()}.
     */
    @Override public void onCreate() {
        super.onCreate();
        subscriptionsAllLifetime = new CompositeSubscription();
    }

    /**
     * Creates <b>CompositeSubscription</b> that lives between {@link #attachView(EliteView view) attachView()} and
     * {@link #detachView()}.
     */
    @UiThread
    @Override public void attachView(V view) {
        super.attachView(view);
        subscriptionsAttachLifetime = new CompositeSubscription();
    }

    /**
     * Unsubscribes from <b>CompositeSubscription</b> that lives between {@link #attachView(EliteView view) attachView()} and
     * {@link #detachView()}.
     */
    @UiThread
    @Override public void detachView() {
        if (subscriptionsAttachLifetime != null && !subscriptionsAttachLifetime.isUnsubscribed())
            subscriptionsAttachLifetime.unsubscribe();

        super.detachView();
    }

    /**
     * Unsubscribes from <b>CompositeSubscription</b> that lives between {@link #onCreate()} and {@link #onDestroy()}.
     */
    @Override public void onDestroy() {
        if (subscriptionsAllLifetime != null && !subscriptionsAllLifetime.isUnsubscribed())
            subscriptionsAllLifetime.unsubscribe();

        super.onDestroy();
    }

}
