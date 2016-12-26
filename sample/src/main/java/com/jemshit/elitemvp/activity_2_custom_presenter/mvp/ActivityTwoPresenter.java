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

package com.jemshit.elitemvp.activity_2_custom_presenter.mvp;

public class ActivityTwoPresenter extends ActivityTwoMvp.Presenter{
    private ActivityTwoMvp.View view;

    // Constructor if needed
    public ActivityTwoPresenter(){
    }

    // Custom attachView(), obligatory to write custom attachView logic now
    @Override
    public void attachView(ActivityTwoMvp.View view) {
        this.view=view;
    }

    // Custom detachView(), obligatory to write custom detachView logic now
    @Override
    public void detachView() {
        view=null;
    }

    @Override
    public void calculateSum(int input1, int input2) {
        if(view!=null)    // Check if View is attached
            view.showSum(String.valueOf(input1+input2));
    }

    // Custom onDestroy
    @Override
    public void onDestroy() {
        // Clear your references, close your instances if needed ...
    }
}
