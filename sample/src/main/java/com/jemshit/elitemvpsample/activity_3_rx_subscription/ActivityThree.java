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

package com.jemshit.elitemvpsample.activity_3_rx_subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.jemshit.elitemvpsample.R;
import com.jemshit.elitemvpsample.activity_3_rx_subscription.mvp.ActivityThreeMvp;
import com.jemshit.elitemvpsample.activity_3_rx_subscription.mvp.ActivityThreePresenter;

public class ActivityThree extends AppCompatActivity implements ActivityThreeMvp.View {

    private TextView textView;

    private ActivityThreeMvp.Presenter presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        getSupportActionBar().setTitle("Activity3: Rx CompositeSubscription");

        presenter = new ActivityThreePresenter();
        presenter.attachView(this);

        // FindViewByIds
        textView = (TextView) findViewById(R.id.textView_activityThree_list);
        AppCompatButton buttonGenerate = (AppCompatButton) findViewById(R.id.button_activityThree_generate);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                presenter.createList();
            }
        });
    }

    @Override @SuppressWarnings("SetTextI18n")
    public void showList(String item) {
        textView.setText(textView.getText() + "\n" + item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();  // presenter.onDetach() is in presenter.onDestroy()
    }

}
