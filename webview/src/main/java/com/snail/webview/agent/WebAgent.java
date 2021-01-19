package com.snail.webview.agent;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.snail.webview.webviewprocess.EasyWebView;

/**
 * 对外暴露的API调用类
 */
public class WebAgent {
    private AgentConfig config;
    private IWebDelegate webDelegate;
    private final Activity activity;

    private WebAgent(Activity activity) {
        this.activity = activity;
    }

    public static WebAgent with(Activity activity) {
        return new WebAgent(activity);
    }

    /**
     * 设置webView的父容器和LayoutParams
     *
     * @param v  父容器
     * @param lp LayoutParams
     */
    public WebAgent setWebAgentParent(@NonNull ViewGroup v, @NonNull ViewGroup.LayoutParams lp) {
        v.addView((View) createWebView(), lp);
        return this;
    }

    /**
     * 应用业务方的各种配置
     *
     * @param config 配置
     */
    public WebAgent applyConfig(AgentConfig config) {
        this.config = config;
        return this;
    }

    /**
     * 触发加载url
     *
     * @param url url
     */
    public void loadUrl(String url) {
        if (webDelegate != null) {
            webDelegate.loadUrl(url);
        }
    }

    /**
     * web是否可返回
     */
    public boolean canGoBack() {
        if (webDelegate != null) {
            return webDelegate.canGoBack();
        }
        return false;
    }

    /**
     * 创建webView
     */
    private IWebDelegate createWebView() {
        if (webDelegate == null) {
            webDelegate = new EasyWebView(activity);
        }
        if (config == null) {
            config = new AgentConfig.Builder().setPullRefresh(true).setShowTitle(false).build();
        }
        return webDelegate.create(config);
    }
}
