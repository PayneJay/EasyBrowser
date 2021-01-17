package com.snail.webview.command;

import com.google.auto.service.AutoService;
import com.snail.common.utils.LogUtil;

@AutoService(Command.class)
public class LoadCompleteCommand implements Command {
    @Override
    public String name() {
        return "loadComplete";
    }

    @Override
    public void execute() {
        LogUtil.i(name());
    }
}
