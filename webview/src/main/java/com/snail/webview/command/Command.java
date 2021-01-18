package com.snail.webview.command;

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
     * @param webView
     * @param params 参数json
     */
    void execute(BaseWebView webView, Map params);
}
