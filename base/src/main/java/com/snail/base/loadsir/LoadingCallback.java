package com.snail.base.loadsir;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.kingja.loadsir.callback.Callback;
import com.snail.base.R;

public class LoadingCallback extends Callback {
    private ImageView ivLoading;

    public LoadingCallback() {
//        initView();
    }

    private void initView() {
        View rootView = getRootView();
        if (rootView == null) {
            return;
        }

        ivLoading = rootView.findViewById(R.id.iv_base_loading);
        Animation animation = new RotateAnimation(0, 359);
        animation.setDuration(500);
        animation.setRepeatCount(Integer.MAX_VALUE);//动画的反复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        ivLoading.startAnimation(animation);//开始动画
    }

    @Override
    protected int onCreateView() {
        return R.layout.loading_layout;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (ivLoading != null) {
            ivLoading.getAnimation().cancel();
        }
    }
}
