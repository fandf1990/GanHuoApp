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
import io.gank.feng24k.app.model.entity.HistoryContentInfo;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.itemModel.HistoryContentItemPresentationModel;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.HistoryContentDetailActivity;
import io.gank.feng24k.app.ui.fragment.HistoryContentFragment;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PresentationModel
public class HistoryContentPresentationModel extends BasePresentationModel implements OnRefreshListener, OnLoadMoreListener {

    private int mPageIndex = 1;
    private int mPageSize = 20;
    private List<HistoryContentInfo> mHistoryContentInfos = new ArrayList<>();
    protected boolean refreshing, loadingMore;
    private HistoryContentFragment mHistoryContentFragment;

    public HistoryContentPresentationModel(HistoryContentFragment fragment) {
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
                .subscribe(new Subscriber<HttpResult<List<HistoryContentInfo>>>() {
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
                    public void onNext(HttpResult<List<HistoryContentInfo>> listHttpResult) {
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


    @ItemPresentationModel(value = HistoryContentItemPresentationModel.class, factoryMethod = "createHistoryContentItemPresentationModel")
    public List<HistoryContentInfo> getHistoryRecyclerSource() {
        return mHistoryContentInfos;
    }


    public HistoryContentItemPresentationModel createHistoryContentItemPresentationModel() {
        return new HistoryContentItemPresentationModel(this);
    }

    public void itemOnClick(int position, HistoryContentInfo entity) {
        Intent intent = new Intent(mHistoryContentFragment.getActivity(), HistoryContentDetailActivity.class);
        intent.putExtra(HistoryContentDetailActivity.INTENT_CONTENT_CODE, entity.getContent());
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
