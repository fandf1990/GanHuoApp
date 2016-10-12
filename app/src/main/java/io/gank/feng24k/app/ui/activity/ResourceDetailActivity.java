package io.gank.feng24k.app.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kennyc.view.MultiStateView;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseMultiStateViewActivity;

public class ResourceDetailActivity extends BaseMultiStateViewActivity implements View.OnClickListener {

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
        setMultiStateViewListener(this);
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

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
            }


        });
        loadUrl();
    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    private void loadUrl() {
        if (TextUtils.isEmpty(mContent)) {
            setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
        } else {
            if (mContent.startsWith("http") || mContent.startsWith("https")) {
                mWebView.loadUrl(mContent);
            } else {
                mWebView.loadDataWithBaseURL(null, mContent, "text/html", "UTF-8", null);
            }
        }
    }


    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("干货详情");
        bar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View v) {
        setMultiViewState(MultiStateView.VIEW_STATE_CONTENT);
        mProgressBar.setVisibility(View.VISIBLE);
        loadUrl();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resource_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.titlebar_share_menu_item) {
            showShareDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showShareDialog(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, mContent);
        intent.setType("text/plain");
        startActivity(intent);
    }
}
