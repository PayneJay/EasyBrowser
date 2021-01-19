package com.snail.easybrowser.webcommand;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.common.utils.LogUtil;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

@AutoService(Command.class)
public class LoginCommand implements Command {
    private IMainProcess2WebProcessInterface iMainProcess2WebProcessInterface;
    private String callbackName;

    public LoginCommand() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(IMainProcess2WebProcessInterface mainProcess2WebProcessInterface, Map params) {
        LogUtil.i(params.toString());
        callbackName = (String) params.get("callbackname");
        this.iMainProcess2WebProcessInterface = mainProcess2WebProcessInterface;
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
            if (iMainProcess2WebProcessInterface != null) {
                String script = "javascript:xiangxuejs.callback('" + callbackName + "',{'accountName':'" + message.obj + "'}" + ")";
                LogUtil.i(script);
                try {
                    iMainProcess2WebProcessInterface.onResult(callbackName, script);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
