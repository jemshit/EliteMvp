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

package com.jemshit.elitemvpsample.sample_6_dagger_injection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jemshit.elitemvpsample.R;

public class Sample6Activity extends AppCompatActivity implements Sample6Contract.View {
    private TextView textSum;
    private EditText input1;
    private EditText input2;

    private Sample6Contract.Presenter presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_adder);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.example_basic));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize Presenter
        presenter = new Sample6Presenter();
        // Attach View to it
        presenter.attachView(this);

        // FindViewByIds, ClickListeners
        textSum = (TextView) findViewById(R.id.text_sampleAdder_sum);
        input1 = (EditText) findViewById(R.id.input_sampleAdder_1);
        input2 = (EditText) findViewById(R.id.input_sampleAdder_2);
        AppCompatButton buttonAdd = (AppCompatButton) findViewById(R.id.button_sampleAdder_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // Call Presenter Method
                if (!TextUtils.isEmpty(input1.getText().toString()) && !TextUtils.isEmpty(input2.getText().toString()))
                    presenter.calculateSum(
                            Integer.parseInt(input1.getText().toString()),
                            Integer.parseInt(input2.getText().toString()));
            }
        });
    }

    // Called by Presenter
    @Override public void showSum(String sum) {
        textSum.setText(sum);
    }

    // Destroy (Detach View from) Presenter
    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
