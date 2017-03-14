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

package com.jemshit.elitemvpsample.activity_2_custom_presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jemshit.elitemvpsample.R;
import com.jemshit.elitemvpsample.activity_2_custom_presenter.mvp.ActivityTwoMvp;
import com.jemshit.elitemvpsample.activity_2_custom_presenter.mvp.ActivityTwoPresenter;
import com.jemshit.elitemvpsample.activity_3_rx_subscription.ActivityThree;

public class ActivityTwo extends AppCompatActivity implements ActivityTwoMvp.View {
    //region Resources
    private TextView textSum;
    private EditText input1;
    private EditText input2;
    AppCompatButton buttonAdd;
    AppCompatButton buttonNextActivity;
    //endregion

    //region Variables
    private ActivityTwoMvp.Presenter presenter;
    //endregion

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getSupportActionBar().setTitle("Activity2: Custom Base Presenter");

        presenter = new ActivityTwoPresenter();
        presenter.attachView(this);

        // FindViewByIds
        textSum = (TextView) findViewById(R.id.textView_activityTwo_sum);
        input1 = (EditText) findViewById(R.id.editText_activityTwo_input1);
        input2 = (EditText) findViewById(R.id.editText_activityTwo_input2);
        buttonAdd = (AppCompatButton) findViewById(R.id.button_activityTwo_add);
        buttonNextActivity = (AppCompatButton) findViewById(R.id.button_activityTwo_nextActivity);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                try {
                    presenter.calculateSum(Integer.parseInt(input1.getText().toString()),
                            Integer.parseInt(input2.getText().toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(ActivityTwo.this, "Number Exception", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startActivity(new Intent(ActivityTwo.this, ActivityThree.class));
            }
        });
    }

    @Override public void showSum(String sum) {
        textSum.setText(sum);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.onDestroy();
    }
}
