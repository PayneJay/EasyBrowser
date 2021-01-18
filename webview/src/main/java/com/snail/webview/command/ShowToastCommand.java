package com.snail.webview.command;

import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.webview.webviewprocess.BaseWebView;

import java.util.Map;

@AutoService(Command.class)
public class ShowToastCommand implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(BaseWebView webView, Map params) {
        Toast.makeText(BaseApplication.sApplication, String.valueOf(params.get("message")), Toast.LENGTH_SHORT).show();
    }
}
