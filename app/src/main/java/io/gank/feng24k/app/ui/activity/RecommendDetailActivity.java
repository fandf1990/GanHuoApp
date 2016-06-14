package io.gank.feng24k.app.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;

public class RecommendDetailActivity extends BaseActivity {

    public static final String INTENT_CONTENT_CODE = "intent_content_code";
    private String mContent;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = getIntent().getStringExtra(INTENT_CONTENT_CODE);
        setContentView(R.layout.recommend_detail_activity, null);
        mWebView = (WebView) findViewById(R.id.history_content_detail_webview);
        if (mContent.startsWith("http")) {
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
        bar.setTitle("每日详情");
        bar.setTitleTextColor(Color.WHITE);
    }


}
