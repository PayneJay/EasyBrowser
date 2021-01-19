package com.snail.webview.webviewprocess;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.snail.base.widget.LoadingStateView;
import com.snail.webview.R;
import com.snail.webview.agent.AgentConfig;
import com.snail.webview.agent.IWebDelegate;
import com.snail.webview.databinding.EasyWebviewLayoutBinding;

import static com.snail.base.widget.LoadingStateView.LoadingType.WITH_DESC_LOADING;

/**
 * 这个Widget集成了下拉刷新、loading加载以及错误页面的展示
 * 这样使得WebView的使用更像是原生
 */
public class EasyWebView extends FrameLayout implements IWebDelegate, IWebViewCallback,
        LoadingStateView.ILoadingStateCallback {
    private EasyWebviewLayoutBinding mBinding;
    private final Context mContext;
    private boolean isError;

    public EasyWebView(@NonNull Context context) {
        this(context, null);
    }

    public EasyWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public IWebDelegate create(AgentConfig config) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.easy_webview_layout, this, false);
        applyConfig(config);
        addView(mBinding.getRoot());
        return this;
    }

    @Override
    public void loadUrl(String url) {
        mBinding.baseWebView.loadUrl(url);
    }

    @Override
    public boolean canGoBack() {
        return false;
    }

    @Override
    public void onPageStarted(String url) {
        isError = false;
        mBinding.loadingView.showLoadingView(WITH_DESC_LOADING);
    }

    @Override
    public void onPageFinished(String url) {
        if (!isError) {
            mBinding.loadingView.hideStateView();
        }
    }

    @Override
    public void updateTitle(String title) {
        mBinding.titleBar.updateTitle(title);
    }

    @Override
    public void onReceivedError(String description, Uri url) {
        isError = true;
        mBinding.loadingView.showErrorView();
    }

    @Override
    public void reload() {
        mBinding.baseWebView.reload();
    }

    private void applyConfig(AgentConfig config) {
        mBinding.titleBar.setVisibility(config.isShowTitle() ? VISIBLE : GONE);
        //根据配置设置是否可以下拉刷新
        mBinding.swipeRefreshLayout.setEnabled(config.isPullRefresh());
        if (config.isPullRefresh()) {
            mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
                mBinding.baseWebView.reload();
                mBinding.swipeRefreshLayout.setRefreshing(false);
            });
        }

        mBinding.baseWebView.setWebViewCallback(this);
        mBinding.loadingView.setLoadingStateCallback(this);
    }

}
