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

package com.jemshit.elitemvpsample.sample_3_rx1_subscription;

import com.jemshit.elitemvp.rx_presenter.EliteRx1Presenter;
import com.jemshit.elitemvp.view.EliteView;

interface Sample3Contract {

    // EliteView
    interface View extends EliteView {
        void showList(String item);
    }

    // EliteRx1Presenter
    abstract class Presenter extends EliteRx1Presenter<View> {
        public abstract void createList();
    }
}
