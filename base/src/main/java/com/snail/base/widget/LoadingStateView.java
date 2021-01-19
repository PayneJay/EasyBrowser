package com.snail.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.snail.base.R;
import com.snail.base.databinding.ViewLoadingStateLayoutBinding;

/**
 * 简易的加载状态组件
 * 包含空状态、加载中、错误页
 */
public class LoadingStateView extends FrameLayout implements LifecycleObserver {
    private final ViewLoadingStateLayoutBinding mBinding;
    private ILoadingStateCallback callback;
    private final Context context;
    private Animation animation;
    private boolean isLoadingState;//是否是loading态
    private LoadingType currentLoadingType;

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
        addView(mBinding.getRoot());
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).getLifecycle().addObserver(this);
        }
    }

    public void setLoadingStateCallback(ILoadingStateCallback callback) {
        this.callback = callback;
    }

    /**
     * 显示空页面
     */
    public void showEmptyView() {
        isLoadingState = false;

        cancelAnim();
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
        isLoadingState = false;

        cancelAnim();
        setVisibility(VISIBLE);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        mBinding.tvStateDesc.setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_error);
        mBinding.tvStateDesc.setText("出错啦，点击重试");
        setOnClickListener(v -> {
            if (currentLoadingType != null) {
                showLoadingView(currentLoadingType);
            }
            if (callback != null) {
                callback.reload();
            }
        });
    }

    /**
     * 显示请求超时
     */
    public void showTimeoutView() {
        isLoadingState = false;

        cancelAnim();
        setVisibility(VISIBLE);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        mBinding.tvStateDesc.setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_timeout);
        mBinding.tvStateDesc.setText("连接超时，点击重试");
        setOnClickListener(v -> {
            if (currentLoadingType != null) {
                showLoadingView(currentLoadingType);
            }
            if (callback != null) {
                callback.reload();
            }
        });
    }

    /**
     * 显示loading页
     */
    public void showLoadingView(LoadingType type) {
        isLoadingState = true;
        currentLoadingType = type;
        setVisibility(VISIBLE);
        mBinding.ivStateIcon.setImageResource(R.drawable.icon_base_loading);
        mBinding.tvStateDesc.setText("加载中...");
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
        animation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim);
        mBinding.ivStateIcon.startAnimation(animation);//開始动画
        setOnClickListener(null);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        if (isLoadingState) {
            if (animation != null) {
                mBinding.ivStateIcon.startAnimation(animation);
                return;
            }

            animation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim);
            mBinding.ivStateIcon.startAnimation(animation);
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        if (animation != null) {
            animation.cancel();
        }
    }

    /**
     * 隐藏状态页
     */
    public void hideStateView() {
        cancelAnim();
        setVisibility(GONE);
    }

    private void cancelAnim() {
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
