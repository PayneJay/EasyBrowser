package com.snail.common.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.snail.common.R;
import com.snail.common.databinding.ViewTitleBarLayoutBinding;

/**
 * 通用TitleBar
 */
public class TitleBar extends FrameLayout {
    private final ViewTitleBarLayoutBinding mBinding;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_title_bar_layout, null, false);
        mBinding.rlBack.setOnClickListener(view -> {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });
    }

    public void updateTitle(String title) {
        mBinding.tvTitle.setText(title);
    }

}
