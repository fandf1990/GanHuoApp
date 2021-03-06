package io.feng24k.app.gank.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.main.RecommendPresentationModel;
import io.feng24k.app.gank.ui.base.BaseFragment;
import io.feng24k.app.gank.ui.widget.GlideImageView;
import io.feng24k.app.gank.ui.widget.binding.GlideImageBinding;
import io.feng24k.app.gank.ui.widget.binding.SwipeToLoadLayoutBinding;

/**
 * 某几日干货网站数据
 */
public class RecommendFragment extends BaseFragment{

    private RecommendPresentationModel mHistoryContentPresentationModel;
    protected RecyclerView mRecyclerView;
    protected SwipeToLoadLayout mSwipeToLoadLayout;


    public static RecommendFragment newInstance(String categoryType) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle bundle  = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.main_swipe_load_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeToLoadLayout.setOnRefreshListener(mHistoryContentPresentationModel);
        mSwipeToLoadLayout.setOnLoadMoreListener(mHistoryContentPresentationModel);
        setErrorViewClickListener(mHistoryContentPresentationModel);
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHistoryContentPresentationModel.autoLoad();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHistoryContentPresentationModel = new RecommendPresentationModel(this);
        return  createView(R.layout.recommend_frag_layout,mHistoryContentPresentationModel);
    }

}
