package com.snail.webview.webviewprocess;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;

import com.snail.webview.R;

/**
 * WebViewClient 配置
 */
public class EasyWebViewClient extends WebViewClient {
    private final IWebViewCallback mCallback;

    public EasyWebViewClient(IWebViewCallback webViewCallback) {
        this.mCallback = webViewCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mCallback != null) {
            mCallback.onPageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mCallback != null) {
            mCallback.onPageFinished(url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mCallback != null) {
            mCallback.onReceivedError(error.toString(), request.getUrl());
            return;
        }
        super.onReceivedError(view, request, error);
    }

    /**
     * Android应用中WebView访问https SSL证书网页时，Google Play 总是报 WebView 的 onReceivedSslError 错误(ERR_SSL_PROTOCOL_ERROR )。
     * 为避免谷歌安全警告，要重写WebView的onReceivedSslError方法，此时要弹框提示用户，是否忽略SSL错误，继续访问网页。
     */
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(R.string.notification_error_ssl_cert_invalid);
        builder.setPositiveButton("继续访问", (dialog, which) -> {
            // 接受所有网站的证书，忽略SSL错误，执行访问网页
            handler.proceed();
        });
        builder.setNegativeButton("取消", (dialog, which) -> handler.cancel());
        final AlertDialog dialog = builder.create();
        dialog.show();

    }
}
