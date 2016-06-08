package io.gank.feng24k.app.model.presentation.main;

import android.content.Intent;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.jiongbull.jlog.JLog;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.http.apiService.CategoryService;
import io.gank.feng24k.app.http.retrofit.RetrofitService;
import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.itemModel.HomeItemPresentationModel;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.MainActivity;
import io.gank.feng24k.app.ui.activity.PhotoViewActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@PresentationModel
public class MainPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener {

    private List<BenefitEntity> mRecyclerSource = new ArrayList<>();
    private boolean refreshing, loadingMore;
    private int mPageIndex = 1;
    private MainActivity mActivity;


    public MainPresentationModel(MainActivity activity) {
        this.mActivity = activity;
    }

    public void autoLoadBenefit() {
        refreshing = true;
        firePropertyChange("refreshing");
    }

    private void getBenefitData() {
        CategoryService categoryService = RetrofitService.getInstance().create(CategoryService.class);
        categoryService.getBenefitData(20, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<List<BenefitEntity>>>() {
                    @Override
                    public void onCompleted() {
                        JLog.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.d("onError " + e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<List<BenefitEntity>> listHttpResult) {
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


    @ItemPresentationModel(value = HomeItemPresentationModel.class ,factoryMethod = "createHomeItemPresentationModel")
    public List<BenefitEntity> getHomeRecyclerSource() {
        return mRecyclerSource;
    }


    public HomeItemPresentationModel createHomeItemPresentationModel() {
        return new HomeItemPresentationModel(this);
    }

    public void itemOnClick(int position, BenefitEntity entity) {
        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.INTENT_PHOTOVIEW_PHOTO_CODE, entity);
        mActivity.startActivity(intent);
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
