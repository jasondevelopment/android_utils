package com.jason.android_utils;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.jason.utils.toast_util.ToastUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Application application = this.getApplication();
        ToastUtils.init(application);

        findViewById(R.id.tv).setOnClickListener(view -> ToastUtils.showToast("哈哈哈哈"));

    }
}