package com.snail.common.autoservice;

import android.content.Context;

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
}
