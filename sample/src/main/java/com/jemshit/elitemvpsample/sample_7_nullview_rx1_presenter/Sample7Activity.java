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

package com.jemshit.elitemvpsample.sample_7_nullview_rx1_presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jemshit.elitemvpsample.R;

public class Sample7Activity extends AppCompatActivity implements Sample7Contract.View {

    private TextView textView;

    private Sample7Contract.Presenter presenter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_rx);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.example_null_view_rx1));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize Presenter
        presenter = new Sample7Presenter(Sample7Contract.View.class);
        // Attach View to it
        presenter.attachView(this);

        // FindViewByIds, ClickListeners
        textView = (TextView) findViewById(R.id.text_sampleRx_list);
        AppCompatButton buttonGenerate = (AppCompatButton) findViewById(R.id.button_sampleRx_generate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // Call Presenter Method
                presenter.createList();
            }
        });
    }

    // Called by Presenter
    @Override @SuppressWarnings("SetTextI18n")
    public void showList(String item) {
        textView.setText(textView.getText() + "\n" + item);

        // Detach View and test
        presenter.detachView();
        Toast.makeText(this, "View is Detached now. Try Again.", Toast.LENGTH_SHORT).show();
    }

    // Destroy (Detach View from) Presenter. Also unsubscribes from Subscriptions
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
