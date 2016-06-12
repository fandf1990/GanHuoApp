package io.gank.feng24k.app.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kennyc.view.MultiStateView;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.presentation.main.CategoryPresentationModel;
import io.gank.feng24k.app.ui.activity.MainActivity;
import io.gank.feng24k.app.ui.base.BaseFragment;
import io.gank.feng24k.app.ui.widget.GlideImageView;
import io.gank.feng24k.app.ui.widget.binding.GlideImageBinding;
import io.gank.feng24k.app.ui.widget.binding.SwipeToLoadLayoutBinding;

public class CategoryFragment extends BaseFragment{

    public static final String FRAGMENT_CATEGORYTYPE_CODE="categoryType";

    private RecyclerView mRecyclerView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private CategoryPresentationModel mCategoryPresentationModel;
    private String mCategoryType;

    public static CategoryFragment newInstance(String categoryType) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle  = new Bundle();
        bundle.putString(FRAGMENT_CATEGORYTYPE_CODE,categoryType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoryType = getArguments().getString(FRAGMENT_CATEGORYTYPE_CODE);
        mCategoryPresentationModel = new CategoryPresentationModel(this,mCategoryType);
        return  createView(R.layout.category_frag_layout,mCategoryPresentationModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.main_swipe_load_layout);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mSwipeToLoadLayout.setOnRefreshListener(mCategoryPresentationModel);
        mSwipeToLoadLayout.setOnLoadMoreListener(mCategoryPresentationModel);
        setMultiViewState(MultiStateView.VIEW_STATE_LOADING);
        mCategoryPresentationModel.autoLoadBenefit();
    }

    public void setMultiViewState(int states){
        ((MainActivity)getActivity()).setMultiViewState(states);
    }

    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(CustomViewBinding.forView(RecyclerView.class, new RecyclerViewBinding()))
                .add(new GlideImageBinding().extend(GlideImageView.class))
                .add(new SwipeToLoadLayoutBinding().extend(SwipeToLoadLayout.class));
    }
}
