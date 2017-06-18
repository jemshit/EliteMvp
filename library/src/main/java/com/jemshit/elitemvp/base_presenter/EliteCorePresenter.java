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

import android.support.annotation.UiThread;

import com.jemshit.elitemvp.view.EliteView;

/**
 * Base <b>Presenter</b> interface with only <b>attachView(V)</b> and <b>detachView()</b>  methods
 *
 * @param <V> instance of {@link EliteView EliteView}
 */
public interface EliteCorePresenter<V extends EliteView> {

    /**
     * To attach {@link EliteView EliteView} instance to this <b>Presenter</b>
     *
     * @param view is instance of {@link EliteView EliteView}
     */
    @UiThread
    void attachView(V view);

    /**
     * To detach {@link EliteView EliteView} instance from this <b>Presenter</b>
     */
    @UiThread
    void detachView();
}