package com.snail.webview.command;

import com.google.auto.service.AutoService;
import com.snail.common.utils.LogUtil;

@AutoService(Command.class)
public class AnswerCommand implements Command {
    @Override
    public String name() {
        return "answer";
    }

    @Override
    public void execute() {
        LogUtil.i(name());
    }
}
