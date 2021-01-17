package com.snail.webview.agent;


public interface IWebDelegate {

    /**
     * 加载url
     *
     * @param url 链接地址
     */
    void loadUrl(String url);

    /**
     * 根据配置创建组件
     *
     * @param config 配置信息
     */
    IWebDelegate create(AgentConfig config);

    /**
     * WebView是否可回退
     *
     * @return true：可回退，false：不可
     */
    boolean canGoBack();
}
