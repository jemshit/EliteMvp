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

package com.jemshit.elitemvpsample.sample_6_dagger_injection.di;

import com.jemshit.elitemvpsample.sample_6_dagger_injection.Sample6Activity;
import com.jemshit.elitemvpsample.sample_6_dagger_injection.Sample6Contract;
import com.jemshit.elitemvpsample.sample_6_dagger_injection.Sample6Presenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = Sample6ActivityComponent.BindsModule.class)
interface Sample6ActivityComponent extends AndroidInjector<Sample6Activity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Sample6Activity> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract Sample6Contract.Presenter providePresenter(Sample6Presenter p);

        @Provides @ActivityScope
        static Class<Sample6Contract.View> provideView() {
            return Sample6Contract.View.class;
        }
    }
}
