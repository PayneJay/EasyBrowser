package com.snail.common.autoservice;

import android.content.Context;
import android.os.Bundle;

/**
 * 下沉接口
 */
public interface IWebViewInterface {

    /**
     * 启动WebViewActivity
     *
     * @param title 标题
     * @param url   web链接
     */
    void startWebViewActivity(Context context, String title, String url);

    /**
     * 启动本地测试H5
     *
     * @param context context
     */
    void startDemoHtml(Context context);

    /**
     * 启动目标页
     *
     * @param context     context
     * @param targetClass 要启动的目标Activity
     * @param bundle      参数
     */
    void openTargetPage(Context context, String targetClass, Bundle bundle);
}
