package com.snail.easybrowser.webcommand;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.common.utils.LogUtil;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.command.Command;

import java.util.Map;

@AutoService(Command.class)
public class OpenPageCommand implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(IMainProcess2WebProcessInterface mainProcess2WebProcessInterface, Map params) {
        String targetClass = String.valueOf(params.get("target_class"));
        LogUtil.i(targetClass);
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
