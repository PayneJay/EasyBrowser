package com.snail.webview.webviewprocess;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * WebView的一些默认设置
 */
public class DefaultWebSettings {
    @SuppressLint("SetJavaScriptEnabled")
    public static void set(WebView webView) {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(false);
        webSetting.setTextZoom(100);
        webSetting.setUseWideViewPort(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSetting.setMediaPlaybackRequiresUserGesture(false);

        webView.setInitialScale(0);
        webView.setFocusable(true);
    }
}
