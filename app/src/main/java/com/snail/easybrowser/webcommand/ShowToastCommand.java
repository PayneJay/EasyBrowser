package com.snail.easybrowser.webcommand;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.webview.IMainProcess2WebProcessInterface;
import com.snail.webview.command.Command;

import java.util.Map;

@AutoService(Command.class)
public class ShowToastCommand implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(IMainProcess2WebProcessInterface mainProcess2WebProcessInterface, Map params) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(BaseApplication.sApplication,
                String.valueOf(params.get("message")), Toast.LENGTH_SHORT).show());

    }
}
