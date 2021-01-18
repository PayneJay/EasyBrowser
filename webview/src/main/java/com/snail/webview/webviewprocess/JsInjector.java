package com.snail.webview.webviewprocess;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.snail.common.utils.LogUtil;
import com.snail.webview.command.Command;
import com.snail.webview.command.CommandFactory;

import java.util.Map;

/**
 * Js注入对象
 */
public class JsInjector {
    private final CommandFactory commandFactory;
    private final BaseWebView webView;

    public JsInjector(BaseWebView webView) {
        this.webView = webView;
        commandFactory = CommandFactory.getInstance();
    }

    @JavascriptInterface
    public void postMessage(String jsonStr) {
        LogUtil.d("jsonStr : " + jsonStr);
        try {
            Map fromJson = new Gson().fromJson(jsonStr, Map.class);
            String name = (String) fromJson.get("name");
            Command command = commandFactory.getCommands().get(name);
            if (command != null) {
                command.execute(webView, new Gson().fromJson(new Gson().toJson(fromJson.get("params")), Map.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d(e.getMessage());
        }
    }
}
