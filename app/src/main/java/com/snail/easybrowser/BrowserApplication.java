package com.snail.easybrowser;

import com.kingja.loadsir.core.LoadSir;
import com.snail.base.BaseApplication;
import com.snail.base.loadsir.CustomCallback;
import com.snail.base.loadsir.EmptyCallback;
import com.snail.base.loadsir.ErrorCallback;
import com.snail.base.loadsir.LoadingCallback;
import com.snail.base.loadsir.TimeoutCallback;

public class BrowserApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }
}
