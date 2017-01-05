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

import android.support.annotation.UiThread;

/**
 * Base <b>presenter</b> interface with 2 methods
 *
 * @param <V> is instance of {@link EliteView EliteView}, which this <b>presenter</b> holds reference to
 */
public interface EliteCorePresenter<V extends EliteView> {

    /**
     * Attach {@link EliteView EliteView} instance to this <b>presenter</b>
     *
     * @param view is instance of {@link EliteView EliteView}
     */
    @UiThread
    void attachView(V view);

    /**
     * Detach {@link EliteView EliteView} from this <b>presenter</b>
     */
    @UiThread
    void detachView();
}