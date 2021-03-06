package io.feng24k.app.gank.model.presentation.main;

import android.content.Intent;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.kennyc.view.MultiStateView;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

import io.feng24k.app.gank.http.HttpSubscriber;
import io.feng24k.app.gank.http.apiService.HistoryService;
import io.feng24k.app.gank.http.retrofit.RetrofitService;
import io.feng24k.app.gank.model.entity.RecommendInfo;
import io.feng24k.app.gank.model.entity.base.HttpResult;
import io.feng24k.app.gank.model.itemModel.RecommendItemPresentationModel;
import io.feng24k.app.gank.model.presentation.BasePresentationModel;
import io.feng24k.app.gank.ui.activity.RecommendDetailActivity;
import io.feng24k.app.gank.ui.fragment.RecommendFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PresentationModel
public class RecommendPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener, View.OnClickListener {

    private int mPageIndex = 1;
    private int mPageSize = 10;
    private List<RecommendInfo> mHistoryContentInfos = new ArrayList<>();
    protected boolean refreshing, loadingMore;
    private RecommendFragment mRecommendFragment;

    public RecommendPresentationModel(RecommendFragment fragment) {
        mRecommendFragment = fragment;
    }

    public void autoLoad() {
        refreshing = true;
        firePropertyChange("refreshing");
    }


    private void getHistoryContent() {
        HistoryService historyService = RetrofitService.getInstance().create(HistoryService.class);
        historyService.getHistoryContent(mPageSize, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<HttpResult<List<RecommendInfo>>>() {

                    @Override
                    public void onFailer(Throwable e) {
                        refreshing = false;
                        loadingMore = false;
                        firePropertyChange("refreshing");
                        firePropertyChange("loadingMore");
                        mRecommendFragment.setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onSuccess(HttpResult<List<RecommendInfo>> listHttpResult) {
                        if (mPageIndex == 1) {
                            refreshing = false;
                            firePropertyChange("refreshing");
                        } else {
                            loadingMore = false;
                            firePropertyChange("loadingMore");
                        }
                        mHistoryContentInfos.addAll(listHttpResult.getResults());
                        firePropertyChange("historyRecyclerSource");
                        mPageIndex++;
                    }
                });
    }


    @ItemPresentationModel(value = RecommendItemPresentationModel.class, factoryMethod = "createHistoryContentItemPresentationModel")
    public List<RecommendInfo> getHistoryRecyclerSource() {
        return mHistoryContentInfos;
    }


    public RecommendItemPresentationModel createHistoryContentItemPresentationModel() {
        return new RecommendItemPresentationModel(this);
    }

    public void itemOnClick(int position, RecommendInfo entity) {
        Intent intent = new Intent(mRecommendFragment.getActivity(), RecommendDetailActivity.class);
        intent.putExtra(RecommendDetailActivity.INTENT_CONTENT_CODE, entity);
        mRecommendFragment.startActivity(intent);
    }

    @Override
    public void onLoadMore() {
        getHistoryContent();
    }

    @Override
    public void onRefresh() {
        mHistoryContentInfos.clear();
        mPageIndex = 1;
        getHistoryContent();
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public boolean isLoadingMore() {
        return loadingMore;
    }

    @Override
    public void onClick(View v) {
        mRecommendFragment.setMultiViewState(MultiStateView.VIEW_STATE_CONTENT);
        autoLoad();
    }
}
