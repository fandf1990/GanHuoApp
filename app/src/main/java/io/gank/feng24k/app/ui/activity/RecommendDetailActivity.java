package io.gank.feng24k.app.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.entity.RecommendInfo;
import io.gank.feng24k.app.ui.base.BaseActivity;

public class RecommendDetailActivity extends BaseActivity {

    public static final String INTENT_CONTENT_CODE = "intent_content_code";
    private RecommendInfo mRecommendInfo;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecommendInfo = getIntent().getParcelableExtra(INTENT_CONTENT_CODE);
        setContentView(R.layout.recommend_detail_activity, null);
        mWebView = (WebView) findViewById(R.id.recommend_detail_webview);

    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("每日推荐");
        bar.setTitleTextColor(Color.WHITE);
    }


}
