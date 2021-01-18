package com.snail.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.snail.base.BaseFragment;
import com.snail.webview.agent.WebAgent;
import com.snail.webview.databinding.FragmentWebViewBinding;

public class WebViewFragment extends BaseFragment {
    private String mUrl;

    private WebViewFragment() {
    }

    public static WebViewFragment create(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.EXTRA_WEB_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(WebConstants.EXTRA_WEB_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentWebViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        WebAgent.with(getActivity()).setWebAgentParent(binding.rlRootView, lp)
                .loadUrl(mUrl);
        return binding.getRoot();
    }
}
