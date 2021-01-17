package com.snail.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.snail.base.R;

public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.empty_layout;
    }

}
