package io.gank.feng24k.app.model.presentation.main;

import android.content.Intent;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.jiongbull.jlog.JLog;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.http.apiService.HistoryService;
import io.gank.feng24k.app.http.retrofit.RetrofitService;
import io.gank.feng24k.app.model.entity.RecommendInfo;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.itemModel.RecommendItemPresentationModel;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.RecommendDetailActivity;
import io.gank.feng24k.app.ui.fragment.RecommendFragment;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PresentationModel
public class RecommendPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener {

    private int mPageIndex = 1;
    private int mPageSize = 10;
    private List<RecommendInfo> mHistoryContentInfos = new ArrayList<>();
    protected boolean refreshing, loadingMore;
    private RecommendFragment mHistoryContentFragment;

    public RecommendPresentationModel(RecommendFragment fragment) {
        mHistoryContentFragment = fragment;
    }

    public void autoLoad() {
        refreshing = true;
        firePropertyChange("refreshing");
        getHistoryContent();
    }


    private void getHistoryContent() {
        HistoryService historyService = RetrofitService.getInstance().create(HistoryService.class);
        historyService.getHistoryContent(mPageSize, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<List<RecommendInfo>>>() {
                    @Override
                    public void onCompleted() {
                        JLog.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.d("onError " + e.getMessage());
                        refreshing = false;
                        loadingMore = false;
                        firePropertyChange("refreshing");
                        firePropertyChange("loadingMore");
                    }

                    @Override
                    public void onNext(HttpResult<List<RecommendInfo>> listHttpResult) {
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
        Intent intent = new Intent(mHistoryContentFragment.getActivity(), RecommendDetailActivity.class);
        intent.putExtra(RecommendDetailActivity.INTENT_CONTENT_CODE, entity.getContent());
        mHistoryContentFragment.startActivity(intent);
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
}
