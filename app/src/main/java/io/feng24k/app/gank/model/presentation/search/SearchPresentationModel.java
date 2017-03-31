package io.feng24k.app.gank.model.presentation.search;

import android.content.Intent;
import android.view.View;

import com.kennyc.view.MultiStateView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.feng24k.app.gank.http.HttpSubscriber;
import io.feng24k.app.gank.http.apiService.SearchService;
import io.feng24k.app.gank.http.retrofit.SearchRetrofitService;
import io.feng24k.app.gank.model.entity.SearchResultInfo;
import io.feng24k.app.gank.model.itemModel.SearchItemPresentationModel;
import io.feng24k.app.gank.model.presentation.BasePresentationModel;
import io.feng24k.app.gank.ui.activity.ResourceDetailActivity;
import io.feng24k.app.gank.ui.activity.SearchActivity;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: fandf
 * Create Date: 2016/10/9.
 * Email: fandf@525happy.cn
 */
@PresentationModel
public class SearchPresentationModel extends BasePresentationModel implements View.OnClickListener{


    private SearchActivity mActivity;
    private List<SearchResultInfo> mSearchSourceList;

    public SearchPresentationModel(SearchActivity activity) {
        this.mActivity = activity;
    }

    public void onBackClick() {
        mActivity.finish();
    }

    private void startSearchByCategory(final String categoryQuery) {
        mActivity.setMultiViewState(MultiStateView.VIEW_STATE_LOADING);
        Observable.create(new Observable.OnSubscribe<List<SearchResultInfo>>() {
            @Override
            public void call(Subscriber<? super List<SearchResultInfo>> subscriber) {
                Call<String> call = SearchRetrofitService.getInstance().create(SearchService.class)
                        .searchByQuery(categoryQuery);
                try {
                    Response<String> response = call.execute();
                    if (response.code() == 200) {
                        List<SearchResultInfo> searchResultInfos = new ArrayList<>();

                        Document document = Jsoup.parse(response.body());
                        Elements elements = document.getElementsByClass("row");
                        for (Element e : elements) {
                            SearchResultInfo searchResultInfo = new SearchResultInfo();

                            Elements linksElements = e.getElementsByTag("a");
                            Elements smallElements = e.getElementsByTag("small");

                            if (smallElements == null || smallElements.size() == 0) {
                                continue;
                            }

                            searchResultInfo.setUrl(linksElements.attr("href"));
                            searchResultInfo.setDesc(linksElements.text());
                            String type = smallElements.get(0).text();
                            searchResultInfo.setType(type.substring(1,type.length()-1));
                            searchResultInfo.setWho(smallElements.get(1).text());

                            searchResultInfos.add(searchResultInfo);
                        }
                        subscriber.onNext(searchResultInfos);
                    } else {
                        subscriber.onError(new IOException());
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<SearchResultInfo>>() {
                    @Override
                    public void onFailer(Throwable e) {
                        mActivity.setMultiViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onSuccess(List<SearchResultInfo> infos) {
                        mSearchSourceList = infos;
                        if(mSearchSourceList!=null&&mSearchSourceList.size()>0){
                            mActivity.setMultiViewState(MultiStateView.VIEW_STATE_CONTENT);
                        }else{
                            mActivity.setMultiViewState(MultiStateView.VIEW_STATE_EMPTY);
                        }
                        firePropertyChange("searchRecyclerSource");
                    }
                });
    }

    public void resetDataEmpty(){
        mSearchSourceList.clear();
        firePropertyChange("searchRecyclerSource");
    }

    @ItemPresentationModel(value = SearchItemPresentationModel.class, factoryMethod = "createSearchItemModel")
    public List<SearchResultInfo> getSearchRecyclerSource() {
        return mSearchSourceList;
    }

    public SearchItemPresentationModel createSearchItemModel() {
        return new SearchItemPresentationModel(this);
    }

    public void onSearchClick(String categoryQuery) {
        startSearchByCategory(categoryQuery);
    }

    public void itemOnClick(int position) {
        Intent intent = new Intent(mActivity, ResourceDetailActivity.class);
        intent.putExtra(ResourceDetailActivity.INTENT_RESOURCE_DETAIL_CODE, mSearchSourceList.get(position).getUrl());
        mActivity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
