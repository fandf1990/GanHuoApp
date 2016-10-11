package io.gank.feng24k.app.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.presentation.search.SearchPresentationModel;
import io.gank.feng24k.app.ui.base.BaseMultiStateViewActivity;

public class SearchActivity extends BaseMultiStateViewActivity {


    private SearchPresentationModel mSearchPresentationModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchPresentationModel = new SearchPresentationModel(this);
        setContentView(R.layout.search_activity,mSearchPresentationModel);
        mSearchPresentationModel.startSearchByCategory();
    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(CustomViewBinding.forView(RecyclerView.class, new RecyclerViewBinding()));
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setVisibility(View.GONE);
        bar.setTitleTextColor(Color.WHITE);
    }
}
