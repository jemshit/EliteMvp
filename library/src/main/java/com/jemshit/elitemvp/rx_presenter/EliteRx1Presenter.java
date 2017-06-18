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

package com.jemshit.elitemvp.rx_presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;

import com.jemshit.elitemvp.base_presenter.ElitePresenter;
import com.jemshit.elitemvp.view.EliteView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * <b>Presenter</b> class with additional "RxJava 1" <b>CompositeSubscription</b> instances.
 * This class holds two <b>CompositeSubscription</b> instances. One instance lives between
 * <b>Constructor</b> and <b>onDestroy()</b>. Second instance lives between <b>attachView(V)</b> and <b>detachView()</b>
 */
@SuppressWarnings("WeakerAccess")
public class EliteRx1Presenter<V extends EliteView> extends ElitePresenter<V> {

    private CompositeSubscription subsAllLifecycle; // Lives between Constructor-onDestroy()
    private CompositeSubscription subsAttachLifecycle; // Lives between onAttach(V)-onDetach()

    /**
     * Creates <b>CompositeSubscription</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    public EliteRx1Presenter() {
        subsAllLifecycle = new CompositeSubscription();
    }

    /**
     * Creates <b>CompositeSubscription</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    @UiThread @Override @CallSuper
    public void attachView(V view) {
        super.attachView(view);
        subsAttachLifecycle = new CompositeSubscription();
    }

    /**
     * Unsubscribes from <b>CompositeSubscription</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    @UiThread @Override @CallSuper
    public void detachView() {
        if (subsAttachLifecycle != null && !subsAttachLifecycle.isUnsubscribed())
            subsAttachLifecycle.unsubscribe();

        super.detachView();
    }

    /**
     * Unsubscribes from <b>CompositeSubscription</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    @Override @CallSuper
    public void onDestroy() {
        if (subsAllLifecycle != null && !subsAllLifecycle.isUnsubscribed())
            subsAllLifecycle.unsubscribe();

        super.onDestroy();
    }

    /**
     * Adds Subscription to <b>CompositeSubscription</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    protected void addToOnAttachSubscriptions(Subscription subscription) {
        subsAttachLifecycle.add(subscription);
    }

    /**
     * Adds Subscription to <b>CompositeSubscription</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    protected void addToOnCreateSubscriptions(Subscription subscription) {
        subsAllLifecycle.add(subscription);
    }

}
