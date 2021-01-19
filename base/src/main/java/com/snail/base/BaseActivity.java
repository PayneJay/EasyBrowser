package com.snail.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.snail.base.databinding.ActivityBaseLayoutBinding;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected ActivityBaseLayoutBinding mBaseBinding;
    protected T mContentBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_layout);
        mBaseBinding.titleBar.updateTitle(getClass().getSimpleName());
        mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(this), getContentLayout(), null, false);
        mBaseBinding.flRootContent.addView(mContentBinding.getRoot());
    }

    /**
     * @return 返回真正的内容布局
     */
    protected abstract @LayoutRes
    int getContentLayout();
}
