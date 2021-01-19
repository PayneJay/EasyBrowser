// IMainProcess2WebProcessInterface.aidl
package com.snail.webview;

// Declare any non-default types here with import statements

interface IMainProcess2WebProcessInterface {
    /**
     * 主进程执行完逻辑后给webview进程的结果回调
     */
    void onResult(String webCallbackName, String params);
}