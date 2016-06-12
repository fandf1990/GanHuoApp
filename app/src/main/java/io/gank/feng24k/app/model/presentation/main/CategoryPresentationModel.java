package io.gank.feng24k.app.model.presentation.main;

import android.content.Intent;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.jiongbull.jlog.JLog;
import com.kennyc.view.MultiStateView;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.http.apiService.CategoryService;
import io.gank.feng24k.app.http.retrofit.RetrofitService;
import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.itemModel.CategoryItemPresentationModel;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.PhotoViewActivity;
import io.gank.feng24k.app.ui.fragment.CategoryFragment;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@PresentationModel
public class CategoryPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener {

    private List<BenefitEntity> mRecyclerSource = new ArrayList<>();
    private boolean refreshing, loadingMore;
    private int mPageIndex = 1;
    private CategoryFragment mCategoryFragment;
    private String mCategoryType;

    public CategoryPresentationModel(CategoryFragment fragment,String categoryType ) {
        this.mCategoryFragment = fragment;
        this.mCategoryType = categoryType;
    }

    public void autoLoadBenefit() {
        //refreshing = true;
        //firePropertyChange("refreshing");
        getBenefitData();
    }

    private void getBenefitData() {
        CategoryService categoryService = RetrofitService.getInstance().create(CategoryService.class);
        categoryService.getBenefitData(mCategoryType,20, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<List<BenefitEntity>>>() {
                    @Override
                    public void onCompleted() {
                        JLog.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.d("onError " + e.getMessage());
                        mCategoryFragment.setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onNext(HttpResult<List<BenefitEntity>> listHttpResult) {
                        mCategoryFragment.setMultiViewState(MultiStateView.VIEW_STATE_CONTENT);
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


    @ItemPresentationModel(value = CategoryItemPresentationModel.class ,factoryMethod = "createHomeItemPresentationModel")
    public List<BenefitEntity> getHomeRecyclerSource() {
        return mRecyclerSource;
    }


    public CategoryItemPresentationModel createHomeItemPresentationModel() {
        return new CategoryItemPresentationModel(this);
    }

    public void itemOnClick(int position, BenefitEntity entity) {
        Intent intent = new Intent(mCategoryFragment.getActivity(), PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.INTENT_PHOTOVIEW_PHOTO_CODE, entity);
        mCategoryFragment.getActivity().startActivity(intent);
    }

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
    public void onLoadMore() {
        getBenefitData();
    }
}
