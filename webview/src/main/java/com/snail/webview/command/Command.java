package com.snail.webview.command;

public interface Command {
    /**
     * 命令名字
     */
    String name();

    /**
     * 命令的具体执行
     */
    void execute();
}
