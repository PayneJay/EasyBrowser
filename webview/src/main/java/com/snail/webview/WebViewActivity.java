package com.snail.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.snail.webview.databinding.ActivityWebViewBinding;
import com.snail.webview.webviewprocess.JsInjector;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding mBinding;
    private String title, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(WebConstants.EXTRA_WEB_TITLE);
            url = bundle.getString(WebConstants.EXTRA_WEB_URL);

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.create(url);
        transaction.replace(mBinding.webViewFragment.getId(), fragment).commit();
    }
}