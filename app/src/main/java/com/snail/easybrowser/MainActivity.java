package com.snail.easybrowser;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.snail.common.autoservice.IWebViewInterface;
import com.snail.easybrowser.databinding.ActivityMainBinding;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private String url = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btnJump.setOnClickListener(v -> {
            IWebViewInterface webViewService = ServiceLoader.load(IWebViewInterface.class).iterator().next();
            if (webViewService != null) {
                webViewService.startWebViewActivity(MainActivity.this, "百度", url);
            }
        });
    }
}