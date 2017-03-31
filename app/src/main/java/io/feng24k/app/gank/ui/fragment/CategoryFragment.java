package io.feng24k.app.gank.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.main.CategoryPresentationModel;
import io.feng24k.app.gank.ui.base.BaseFragment;
import io.feng24k.app.gank.ui.widget.GlideImageView;
import io.feng24k.app.gank.ui.widget.binding.GlideImageBinding;
import io.feng24k.app.gank.ui.widget.binding.SwipeToLoadLayoutBinding;

public class CategoryFragment extends BaseFragment{

    public static final String FRAGMENT_CATEGORYTYPE_CODE="categoryType";
    protected RecyclerView mRecyclerView;
    protected SwipeToLoadLayout mSwipeToLoadLayout;
    protected String mCategoryType;
    protected CategoryPresentationModel mCategoryPresentationModel;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCategoryType = getArguments().getString(FRAGMENT_CATEGORYTYPE_CODE);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.main_swipe_load_layout);
        mSwipeToLoadLayout.setOnRefreshListener(mCategoryPresentationModel);
        mSwipeToLoadLayout.setOnLoadMoreListener(mCategoryPresentationModel);
        setErrorViewClickListener(mCategoryPresentationModel);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCategoryPresentationModel.autoLoadBenefit(mCategoryType);
            }
        },800);
    }

    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(CustomViewBinding.forView(RecyclerView.class, new RecyclerViewBinding()))
                .add(new GlideImageBinding().extend(GlideImageView.class))
                .add(new SwipeToLoadLayoutBinding().extend(SwipeToLoadLayout.class));
    }
}
