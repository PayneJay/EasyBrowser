package com.snail.webview.webviewprocess;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.snail.common.utils.LogUtil;

import java.util.Map;

/**
 * Js注入对象
 */
public class JsInjector {
    private BaseWebView webView;

    public JsInjector(BaseWebView webView) {
        this.webView = webView;
    }

    @JavascriptInterface
    public void postMessage(String jsonStr) {
        LogUtil.d("jsonStr : " + jsonStr);
        try {
            Map fromJson = new Gson().fromJson(jsonStr, Map.class);
            String name = (String) fromJson.get("name");
            WebProcessCommandDispatcher.getInstance().executeCommand(name,
                    new Gson().toJson(fromJson.get("params")), webView);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(e.getMessage());
        }
    }
}
