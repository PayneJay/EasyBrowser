package com.snail.webview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.auto.service.AutoService;
import com.snail.common.autoservice.IWebService;

@AutoService(IWebService.class)
public class WebViewServiceImpl implements IWebService {

    @Override
    public void startWebViewActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.EXTRA_WEB_URL, url);
        bundle.putString(WebConstants.EXTRA_WEB_TITLE, title);
        intent.putExtras(bundle);

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public void startDemoHtml(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.EXTRA_WEB_URL, WebConstants.ANDROID_ASSET_URI + "demo.html");
        bundle.putString(WebConstants.EXTRA_WEB_TITLE, "本地Demo测试页");
        intent.putExtras(bundle);

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    public void openTargetPage(Context context, String targetClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, targetClass));
        intent.putExtras(bundle);

        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
