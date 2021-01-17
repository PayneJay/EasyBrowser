package com.snail.webview.agent;

public class AgentConfig {
    /**
     * 是否可以下拉刷新
     */
    private final boolean pullRefresh;
    /**
     * 是否展示标题
     */
    private final boolean showTitle;

    public boolean isPullRefresh() {
        return pullRefresh;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public AgentConfig(Builder builder) {
        this.pullRefresh = builder.pullRefresh;
        this.showTitle = builder.showTitle;
    }

    public static class Builder {
        private boolean pullRefresh;
        private boolean showTitle;

        public Builder setPullRefresh(boolean pullRefresh) {
            this.pullRefresh = pullRefresh;
            return this;
        }

        public Builder setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return this;
        }

        public AgentConfig build() {
            return new AgentConfig(this);
        }
    }
}
