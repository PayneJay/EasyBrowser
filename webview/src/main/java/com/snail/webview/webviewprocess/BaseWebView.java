package com.snail.webview.webviewprocess;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.snail.common.utils.LogUtil;

/**
 * WebView基类，做一些基础的设置
 */
public class BaseWebView extends WebView implements IWebViewCallback {
    private IWebViewCallback iWebViewCallback;

    public BaseWebView(@NonNull Context context) {
        this(context, null);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setWebViewCallback(IWebViewCallback iWebViewCallback) {
        this.iWebViewCallback = iWebViewCallback;
    }

    private void init() {
        DefaultWebSettings.set(this);
        setWebViewClient(new EasyWebViewClient(this));
        setWebChromeClient(new EasyWebChromeClient(this));
        addJavascriptInterface(new JsInjector(), "JsInjector");
    }

    @Override
    public void onPageStarted(String url) {
        LogUtil.d(url);
        if (iWebViewCallback != null) {
            iWebViewCallback.onPageStarted(url);
        }
    }

    @Override
    public void onPageFinished(String url) {
        LogUtil.d(url);
        if (iWebViewCallback != null) {
            iWebViewCallback.onPageFinished(url);
        }
    }

    @Override
    public void updateTitle(String title) {
        LogUtil.d(title);
        if (iWebViewCallback != null) {
            iWebViewCallback.updateTitle(title);
        }
    }

    @Override
    public void onReceivedError(String description, Uri url) {
        LogUtil.d("description : " + description + "，url : " + url);
        if (iWebViewCallback != null) {
            iWebViewCallback.onReceivedError(description, url);
        }
    }

}
