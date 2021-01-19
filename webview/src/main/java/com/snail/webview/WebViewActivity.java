package com.snail.webview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.snail.base.BaseActivity;
import com.snail.webview.databinding.ActivityWebViewBinding;

public class WebViewActivity extends BaseActivity<ActivityWebViewBinding> {
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString(WebConstants.EXTRA_WEB_URL);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.create(url);
        transaction.replace(mContentBinding.webViewFragment.getId(), fragment).commit();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_web_view;
    }
}