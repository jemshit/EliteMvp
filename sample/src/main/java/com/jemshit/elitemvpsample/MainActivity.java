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

package com.jemshit.elitemvpsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jemshit.elitemvpsample.sample_1_basic_presenter.Sample1Activity;
import com.jemshit.elitemvpsample.sample_2_custom_presenter.Sample2Activity;
import com.jemshit.elitemvpsample.sample_3_rx1_subscription.Sample3Activity;
import com.jemshit.elitemvpsample.sample_4_rx2_disposable.Sample4Activity;
import com.jemshit.elitemvpsample.sample_5_nullview_presenter.Sample5Activity;
import com.jemshit.elitemvpsample.sample_6_dagger_injection.Sample6Activity;

public class MainActivity extends AppCompatActivity {

    private Button buttonS1;
    private Button buttonS2;
    private Button buttonS3;
    private Button buttonS4;
    private Button buttonS5;
    private Button buttonS6;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }

        findViewByIds();
        setClickListeners();
    }

    private void findViewByIds() {
        buttonS1 = (Button) findViewById(R.id.button_mainActivity_s1);
        buttonS2 = (Button) findViewById(R.id.button_mainActivity_s2);
        buttonS3 = (Button) findViewById(R.id.button_mainActivity_s3);
        buttonS4 = (Button) findViewById(R.id.button_mainActivity_s4);
        buttonS5 = (Button) findViewById(R.id.button_mainActivity_s5);
        buttonS6 = (Button) findViewById(R.id.button_mainActivity_s6);
    }

    private void setClickListeners() {
        buttonS1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample1Activity.class));
            }
        });

        buttonS2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample2Activity.class));
            }
        });

        buttonS3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample3Activity.class));
            }
        });

        buttonS4.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample4Activity.class));
            }
        });

        buttonS5.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample5Activity.class));
            }
        });

        buttonS6.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sample6Activity.class));
            }
        });
    }
}
