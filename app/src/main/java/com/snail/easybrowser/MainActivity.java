package com.snail.easybrowser;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.snail.base.BaseActivity;
import com.snail.common.autoservice.IWebService;
import com.snail.easybrowser.databinding.ActivityMainBinding;

import java.util.ServiceLoader;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private final String url = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.btnJump.setOnClickListener(v -> {
            IWebService webViewService = ServiceLoader.load(IWebService.class).iterator().next();
            if (webViewService != null) {
                webViewService.startWebViewActivity(MainActivity.this, "百度", url);
            }
        });

        mBinding.btnStartDemo.setOnClickListener(v -> {
            IWebService webViewService = ServiceLoader.load(IWebService.class).iterator().next();
            if (webViewService != null) {
                webViewService.startDemoHtml(MainActivity.this);
            }
        });
    }
}