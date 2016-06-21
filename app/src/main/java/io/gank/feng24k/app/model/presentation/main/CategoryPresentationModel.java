package io.gank.feng24k.app.model.presentation.main;

import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.kennyc.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.http.HttpSubscriber;
import io.gank.feng24k.app.http.apiService.CategoryService;
import io.gank.feng24k.app.http.retrofit.RetrofitService;
import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.base.BaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public abstract class CategoryPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener ,View.OnClickListener{

    protected List<BenefitEntity> mRecyclerSource = new ArrayList<>();
    protected boolean refreshing, loadingMore;
    protected int mPageIndex = 1;
    protected int mPageSize = 20;
    protected BaseFragment mCategoryFragment;
    protected String mCategoryType;

    public CategoryPresentationModel(BaseFragment fragment) {
        this.mCategoryFragment = fragment;
    }

    public void autoLoadBenefit(String categoryType) {
        this.mCategoryType = categoryType;
        refreshing = true;
        firePropertyChange("refreshing");
        getBenefitData();
    }

    private void getBenefitData() {
        CategoryService categoryService = RetrofitService.getInstance().create(CategoryService.class);
        categoryService.getBenefitData(mCategoryType, mPageSize, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<HttpResult<List<BenefitEntity>>>() {

                    @Override
                    public void onFailer(Throwable e) {
                        refreshing = false;
                        loadingMore = false;
                        firePropertyChange("refreshing");
                        firePropertyChange("loadingMore");
                        mCategoryFragment.setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onSuccess(HttpResult<List<BenefitEntity>> listHttpResult) {
                        if (mPageIndex == 1) {
                            refreshing = false;
                            firePropertyChange("refreshing");
                        } else {
                            loadingMore = false;
                            firePropertyChange("loadingMore");
                        }
                        mRecyclerSource.addAll(listHttpResult.getResults());
                        firePropertyChange("homeRecyclerSource");
                        mPageIndex++;
                    }
                });
    }

    public abstract void itemOnClick(int position, BenefitEntity entity);

    public boolean getRefreshing() {
        return refreshing;
    }

    public boolean getLoadingMore() {
        return loadingMore;
    }


    @Override
    public void onRefresh() {
        mRecyclerSource.clear();
        mPageIndex = 1;
        getBenefitData();
    }

    @Override
    public void onClick(View v) {
        mCategoryFragment.setMultiViewState(MultiStateView.VIEW_STATE_CONTENT);
        autoLoadBenefit(this.mCategoryType);
    }

    @Override
    public void onLoadMore() {
        getBenefitData();
    }
}
