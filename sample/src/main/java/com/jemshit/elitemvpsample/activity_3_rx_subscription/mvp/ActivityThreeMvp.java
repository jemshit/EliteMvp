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

package com.jemshit.elitemvpsample.activity_3_rx_subscription.mvp;

import com.jemshit.elitemvp.EliteRxPresenter;
import com.jemshit.elitemvp.EliteView;

public interface ActivityThreeMvp {
    interface View extends EliteView {
        void showList(String item);
    }

    abstract class Presenter extends EliteRxPresenter<View> {
        public abstract void createList();
    }
}
