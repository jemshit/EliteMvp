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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ActivityThreePresenter extends ActivityThreeMvp.Presenter {
    private final Random randomGenerator;

    public ActivityThreePresenter() {
        super.onCreate();
        randomGenerator = new Random();
    }

    @Override public void createList() {
        List<String> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(randomGenerator.nextInt(101 - 50) + 50));   // Random number between [50-100]
        }
        // Add this Subscription to CompositeSubscription (subscriptionsAllLifetime)
        subscriptionsAllLifetime.add(
                Observable.from(list)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {
                            @Override public void onCompleted() {

                            }
                            @Override public void onError(Throwable e) {

                            }
                            @Override public void onNext(String s) {
                                if(isViewAttached())
                                    getView().showList(s);
                            }
                        })
        );
    }

}

