package io.gank.feng24k.app.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;

public class ResourceDetailActivity extends BaseActivity {

    public static final String INTENT_RESOURCE_DETAIL_CODE = "intent_resource_detail_code";
    private String mContent;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = getIntent().getStringExtra(INTENT_RESOURCE_DETAIL_CODE);
        setContentView(R.layout.resource_detail_activity, null);
        mWebView = (WebView) findViewById(R.id.resource_detail_webview);
        mProgressBar = (ProgressBar) findViewById(R.id.resource_detail_progress_bar);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }

        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        if (mContent.startsWith("http") || mContent.startsWith("https")) {
            mWebView.loadUrl(mContent);
        } else {
            mWebView.loadDataWithBaseURL(null, mContent, "text/html", "UTF-8", null);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("干货详情");
        bar.setTitleTextColor(Color.WHITE);
    }

}
