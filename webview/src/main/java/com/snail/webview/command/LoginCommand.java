package com.snail.webview.command;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.common.utils.LogUtil;
import com.snail.webview.webviewprocess.BaseWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

@AutoService(Command.class)
public class LoginCommand implements Command {
    private BaseWebView webView;
    private String callbackName;

    public LoginCommand() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(BaseWebView webView, Map params) {
        LogUtil.i(params.toString());
        callbackName = (String) params.get("callbackname");
        this.webView = webView;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.sApplication, "com.snail.login.ui.login.LoginActivity"));
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }

    @Subscribe
    public void onMessageEvent(Message message) {
        if (message != null) {
            LogUtil.d("message.what : " + message.obj);
            if (webView != null) {
                String script = "javascript:xiangxuejs.callback('" + callbackName + "',{'accountName':'" + message.obj + "'}" + ")";
                LogUtil.i(script);
                webView.evaluateJavascript(script, null);
            }
        }
    }
}
