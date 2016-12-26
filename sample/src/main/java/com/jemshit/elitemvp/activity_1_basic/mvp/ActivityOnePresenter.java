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

package com.jemshit.elitemvp.activity_1_basic.mvp;

public class ActivityOnePresenter extends ActivityOneMvp.Presenter{

    // Constructor if needed
    public ActivityOnePresenter(){
        super.onCreate();   // You can also call this from Activity.onCreate()
    }

    @Override public void calculateSum(int input1, int input2) {
        if(isViewAttached())    // Check if View is attached
            getView().showSum(String.valueOf(input1+input2));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Clear your references, close your instances if needed ...
    }
}
