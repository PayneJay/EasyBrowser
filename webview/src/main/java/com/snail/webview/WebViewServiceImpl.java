package com.snail.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.auto.service.AutoService;
import com.snail.common.autoservice.IWebViewInterface;

@AutoService(IWebViewInterface.class)
public class WebViewServiceImpl implements IWebViewInterface {

    @Override
    public void startWebViewActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.EXTRA_WEB_URL, url);
        bundle.putString(WebConstants.EXTRA_WEB_TITLE, title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
