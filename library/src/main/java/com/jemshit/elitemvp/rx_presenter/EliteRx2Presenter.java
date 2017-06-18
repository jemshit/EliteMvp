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

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <b>Presenter</b> class with additional "RxJava 2" <b>CompositeDisposable</b> instances.
 * This class holds two <b>CompositeDisposable</b> instances. One instance lives between
 * <b>Constructor</b> and <b>onDestroy()</b>. Second instance lives between <b>attachView(V)</b> and <b>detachView()</b>
 */
@SuppressWarnings("WeakerAccess")
public class EliteRx2Presenter<V extends EliteView> extends ElitePresenter<V> {

    private CompositeDisposable disposablesAllLifecycle; // Lives between Constructor-onDestroy()
    private CompositeDisposable disposablesAttachLifecycle; // Lives between onAttach(V)-onDetach()

    /**
     * Creates <b>CompositeDisposable</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    public EliteRx2Presenter() {
        disposablesAllLifecycle = new CompositeDisposable();
    }

    /**
     * Creates <b>CompositeDisposable</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    @UiThread @Override @CallSuper
    public void attachView(V view) {
        super.attachView(view);
        disposablesAttachLifecycle = new CompositeDisposable();
    }

    /**
     * Unsubscribes from <b>CompositeDisposable</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    @UiThread @Override @CallSuper
    public void detachView() {
        if (disposablesAttachLifecycle != null && !disposablesAttachLifecycle.isDisposed())
            disposablesAttachLifecycle.dispose();

        super.detachView();
    }

    /**
     * Unsubscribes from <b>CompositeDisposable</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    @Override @CallSuper
    public void onDestroy() {
        if (disposablesAllLifecycle != null && !disposablesAllLifecycle.isDisposed())
            disposablesAllLifecycle.dispose();

        super.onDestroy();
    }

    /**
     * Adds Disposable to <b>CompositeDisposable</b> that lives between <b>attachView(V)</b> and <b>detachView()</b>
     */
    protected void addToOnAttachDisposables(Disposable disposable) {
        disposablesAttachLifecycle.add(disposable);
    }

    /**
     * Adds Disposable to <b>CompositeDisposable</b> that lives between <b>Constructor</b> and <b>onDestroy()</b>.
     */
    protected void addToOnCreateDisposables(Disposable disposable) {
        disposablesAllLifecycle.add(disposable);
    }

}
