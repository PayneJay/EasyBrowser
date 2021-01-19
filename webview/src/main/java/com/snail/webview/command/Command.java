package com.snail.webview.command;

import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.webviewprocess.BaseWebView;

import java.util.Map;

public interface Command {
    /**
     * 命令名字
     */
    String name();

    /**
     * 命令的具体执行
     *
     * @param mainProcess2WebProcessInterface 主进程到webview进程的回调
     * @param params                          参数json
     */
    void execute(IMainProcess2WebProcessInterface mainProcess2WebProcessInterface, Map params);
}
