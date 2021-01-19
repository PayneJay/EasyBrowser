package com.snail.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.snail.base.BaseApplication;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.IWebProcess2MainProcessInterface;
import com.snail.webview.mainprocess.MainProcessService;

/**
 * webview进程命令分发器
 */
public class WebProcessCommandDispatcher implements ServiceConnection {
    private static volatile WebProcessCommandDispatcher sInstance;
    private IWebProcess2MainProcessInterface webProcess2MainProcessInterface;

    private WebProcessCommandDispatcher() {

    }

    public static WebProcessCommandDispatcher getInstance() {
        if (null == sInstance) {
            synchronized (WebProcessCommandDispatcher.class) {
                if (null == sInstance) {
                    sInstance = new WebProcessCommandDispatcher();
                }
            }
        }

        return sInstance;
    }

    public void initAidlConnection() {
        Intent service = new Intent(BaseApplication.sApplication, MainProcessService.class);
        BaseApplication.sApplication.bindService(service, this, Context.BIND_AUTO_CREATE);
    }

    public void executeCommand(String commandName, String params, BaseWebView webView) {
        if (webProcess2MainProcessInterface != null) {
            try {
                webProcess2MainProcessInterface.handleWebCommand(commandName, params, new IMainProcess2WebProcessInterface.Stub() {
                    @Override
                    public void onResult(String webCallbackName, String params) throws RemoteException {
                        webView.handleCallback(webCallbackName, params);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        webProcess2MainProcessInterface = IWebProcess2MainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        webProcess2MainProcessInterface = null;
        initAidlConnection();
    }
}
