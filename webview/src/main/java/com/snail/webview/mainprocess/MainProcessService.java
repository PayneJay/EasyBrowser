package com.snail.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MainProcessService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return CommandFactory.getInstance();
    }
}
