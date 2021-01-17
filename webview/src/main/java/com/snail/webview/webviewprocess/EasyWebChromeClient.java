package com.snail.webview.webviewprocess;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.snail.common.utils.LogUtil;

/**
 * WebChromeClient 配置
 */
public class EasyWebChromeClient extends WebChromeClient {
    private final IWebViewCallback mCallback;

    public EasyWebChromeClient(IWebViewCallback webViewCallback) {
        this.mCallback = webViewCallback;
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        LogUtil.d(consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mCallback != null) {
            mCallback.updateTitle(title);
        }
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        LogUtil.d("currentProgress : " + newProgress + "%");
    }
}
