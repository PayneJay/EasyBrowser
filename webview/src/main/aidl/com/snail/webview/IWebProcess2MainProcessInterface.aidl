// IWebProcess2MainProcessInterface.aidl
package com.snail.webview;

// Declare any non-default types here with import statements
import com.snail.webview.IMainProcess2WebProcessInterface;

interface IWebProcess2MainProcessInterface {
    /**
     * 处理webview进程处理主进程的命令
     */
    void handleWebCommand(String commandName, String params, in IMainProcess2WebProcessInterface mainProcess2WebProcess);
}