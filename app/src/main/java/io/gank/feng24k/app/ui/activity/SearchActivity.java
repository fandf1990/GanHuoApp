package io.gank.feng24k.app.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;

public class SearchActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity,null);
    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("搜索");
        bar.setTitleTextColor(Color.WHITE);
    }
}
