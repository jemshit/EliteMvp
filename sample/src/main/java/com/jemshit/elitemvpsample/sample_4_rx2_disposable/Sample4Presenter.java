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

package com.jemshit.elitemvpsample.sample_4_rx2_disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


class Sample4Presenter extends Sample4Contract.Presenter {
    private final Random randomGenerator;

    Sample4Presenter() {
        randomGenerator = new Random();
    }

    @Override public void createList() {
        List<String> list = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            // Random number between [50-100]
            list.add(String.valueOf(randomGenerator.nextInt(101 - 50) + 50));
        }

        Observable<String> stringObservable =
                Observable.fromIterable(list)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        // Add this Disposable to CompositeDisposable (that lives between Constructor-onDestroy())
        addToOnCreateDisposables(
                stringObservable.subscribeWith(new DisposableObserver<String>() {

                    @Override public void onNext(@NonNull String s) {
                        if (isViewAttached())
                            getView().showList(s);
                    }

                    @Override public void onError(@NonNull Throwable e) {
                        // example: getView().showError(e.getMessage())
                    }

                    @Override public void onComplete() {

                    }
                })
        );
    }
}

