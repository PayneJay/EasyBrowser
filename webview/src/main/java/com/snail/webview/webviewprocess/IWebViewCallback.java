package com.snail.webview.webviewprocess;

import android.net.Uri;

/**
 * WebView各种回调
 */
public interface IWebViewCallback {
    /**
     * 页面开始加载
     *
     * @param url url
     */
    void onPageStarted(String url);

    /**
     * 页面加载结束
     * ⚠️即使加载失败这个回调还是会走
     *
     * @param url url
     */
    void onPageFinished(String url);

    /**
     * 有时候我们需要使用网页的标题
     *
     * @param title title
     */
    void updateTitle(String title);

    /**
     * WebView加载失败/错误
     *
     * @param description 错误描述
     * @param url         错误的uri
     */
    void onReceivedError(String description, Uri url);
}
