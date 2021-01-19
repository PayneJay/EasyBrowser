package com.snail.easybrowser;


import android.os.Bundle;

import com.snail.base.BaseActivity;
import com.snail.base.serviceloader.EasyServiceLoader;
import com.snail.common.autoservice.IWebService;
import com.snail.easybrowser.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private final String url = "https://www.baidu.com";
    IWebService webViewService = EasyServiceLoader.load(IWebService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mContentBinding.btnJump.setOnClickListener(v -> {
            if (webViewService != null) {
                webViewService.startWebViewActivity(MainActivity.this, "百度", url);
            }
        });

        mContentBinding.btnStartDemo.setOnClickListener(v -> {
            if (webViewService != null) {
                webViewService.startDemoHtml(MainActivity.this);
            }
        });
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }
}