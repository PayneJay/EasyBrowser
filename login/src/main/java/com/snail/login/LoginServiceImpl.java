package com.snail.login;

import android.content.ComponentName;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.snail.base.BaseApplication;
import com.snail.login.autoservice.ILoginService;
import com.snail.login.ui.login.LoginActivity;

@AutoService(ILoginService.class)
public class LoginServiceImpl implements ILoginService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.sApplication, LoginActivity.class));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//用application的context启动必须加这个
        BaseApplication.sApplication.startActivity(intent);
    }
}
