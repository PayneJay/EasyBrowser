package com.snail.easybrowser.webcommand;

import android.os.Message;
import android.os.RemoteException;

import com.google.auto.service.AutoService;
import com.snail.common.utils.LogUtil;
import com.snail.login.autoservice.ILoginService;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;
import java.util.ServiceLoader;

@AutoService(Command.class)
public class LoginCommand implements Command {
    private IMainProcess2WebProcessInterface iMainProcess2WebProcessInterface;
    private ILoginService iLoginService;
    private String callbackName;

    public LoginCommand() {
        EventBus.getDefault().register(this);
        iLoginService = ServiceLoader.load(ILoginService.class).iterator().next();
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(IMainProcess2WebProcessInterface mainProcess2WebProcessInterface, Map params) {
        callbackName = (String) params.get("callbackname");
        this.iMainProcess2WebProcessInterface = mainProcess2WebProcessInterface;
        if (iLoginService != null && !iLoginService.isLogin()) {
            iLoginService.login();
        }
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
