package com.snail.base.loadsir;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.snail.base.R;

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.error_layout;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
