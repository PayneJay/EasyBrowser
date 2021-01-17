package com.snail.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.snail.common.R;
import com.snail.common.databinding.ViewLoadingStateLayoutBinding;

/**
 * 简易的加载状态组件
 * 包含空状态、加载中、错误页
 */
public class LoadingStateView extends FrameLayout {
    private final ViewLoadingStateLayoutBinding mBinding;
    private ILoadingStateCallback callback;
    private final Context context;
    private Animation animation;

    public LoadingStateView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.view_loading_state_layout, null, false);
    }

    public void setLoadingStateCallback(ILoadingStateCallback callback) {
        this.callback = callback;
    }

    /**
     * 显示空页面
     */
    public void showEmptyView() {
        setVisibility(VISIBLE);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        mBinding.tvStateDesc.setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_empty);
        mBinding.tvStateDesc.setText("暂时还没有数据喔");
        setOnClickListener(null);
    }

    /**
     * 显示错误页
     */
    public void showErrorView() {
        setVisibility(VISIBLE);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        mBinding.tvStateDesc.setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_error);
        mBinding.tvStateDesc.setText("出错啦，点击重试");
        setOnClickListener(v -> {
            showLoadingView(LoadingType.FULL_SCREEN_LOADING);
            if (callback != null) {
                callback.reload();
            }
        });
    }

    /**
     * 显示请求超时
     */
    public void showTimeoutView() {
        setVisibility(VISIBLE);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        mBinding.tvStateDesc.setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_timeout);
        mBinding.tvStateDesc.setText("连接超时，点击重试");
        setOnClickListener(v -> {
            showLoadingView(LoadingType.FULL_SCREEN_LOADING);
            if (callback != null) {
                callback.reload();
            }
        });
    }

    /**
     * 显示loading页
     */
    public void showLoadingView(LoadingType type) {
        setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_loading);
        mBinding.tvStateDesc.setText("加载中...");

        animation = new RotateAnimation(0, 359);
        animation.setDuration(500);
        animation.setRepeatCount(Animation.INFINITE);//动画的反复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        mBinding.ivStateIcon.startAnimation(animation);//開始动画
        switch (type) {
            case CENTER_BG_LOADING:
                setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                mBinding.tvStateDesc.setVisibility(GONE);
                break;
            case FULL_SCREEN_LOADING:
                setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                mBinding.tvStateDesc.setVisibility(GONE);
                break;
            case WITH_DESC_LOADING:
                setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
                mBinding.tvStateDesc.setVisibility(VISIBLE);
                break;
        }
    }

    /**
     * 隐藏状态页
     */
    public void hideView() {
        setVisibility(GONE);
        if (animation != null) {
            animation.cancel();
        }
    }

    /**
     * Loading样式枚举
     */
    public enum LoadingType {
        /**
         * 全屏loading，白底
         */
        FULL_SCREEN_LOADING,
        /**
         * 白底loading
         */
        CENTER_BG_LOADING,
        /**
         * 白底loading，带加载描述
         */
        WITH_DESC_LOADING
    }

    public interface ILoadingStateCallback {
        void reload();
    }
}
