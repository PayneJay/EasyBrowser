package com.snail.webview.webviewprocess;

import android.webkit.JavascriptInterface;

import com.snail.webview.command.Command;
import com.snail.webview.command.CommandFactory;

import org.json.JSONObject;

public class JsInjector {

    @JavascriptInterface
    public void postMessage(String tag, String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String type = (String) jsonObject.get("type");
            Command command = CommandFactory.getInstance().getCommand();
            if (command != null) {
                command.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
