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

package com.jemshit.elitemvpsample.sample_5_nullview_presenter;

import com.jemshit.elitemvp.null_view_presenter.EliteNullViewPresenter;
import com.jemshit.elitemvp.view.EliteView;

interface Sample5Contract {

    // EliteView
    interface View extends EliteView {
        void showSum(String sum);
    }

    // EliteNullViewPresenter
    abstract class Presenter extends EliteNullViewPresenter<View> {

        public Presenter(Class<View> viewType) {
            super(viewType);
        }

        public abstract void calculateSum(int input1, int input2);
    }
}
