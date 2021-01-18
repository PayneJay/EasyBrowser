package com.snail.easybrowser;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.snail.base.BaseActivity;

public class TestActivityA extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_a);
    }
}
