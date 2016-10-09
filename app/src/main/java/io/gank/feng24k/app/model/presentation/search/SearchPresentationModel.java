package io.gank.feng24k.app.model.presentation.search;

import com.jiongbull.jlog.JLog;

import org.robobinding.annotation.PresentationModel;

import io.gank.feng24k.app.http.apiService.SearchService;
import io.gank.feng24k.app.http.retrofit.RetrofitService;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.SearchActivity;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: fandf
 * Create Date: 2016/10/9.
 * Email: fandf@525happy.cn
 */
@PresentationModel
public class SearchPresentationModel extends BasePresentationModel {

    private int mSelectedSourceIndex;
    private SearchActivity mActivity;
    private String mCategoryQuery;
    private int mPageSize = 10;
    private int mPageIndex = 1;

    public SearchPresentationModel(SearchActivity activity){
        this.mActivity = activity;
    }

    public void setSelectedSourceIndex(int selectedSourceIndex) {
        mSelectedSourceIndex = selectedSourceIndex;
    }

    public int getSelectedSourceIndex() {
        return mSelectedSourceIndex;
    }

    public void onBackClick(){
        mActivity.finish();
    }

    public String getCategoryQuery() {
        return mCategoryQuery;
    }

    public void setCategoryQuery(String categoryQuery) {
        mCategoryQuery = categoryQuery;
    }

    public void startSearchByCategory(){
        SearchService searchService = RetrofitService.getInstance().create(SearchService.class);
        searchService.searchByCategory(mCategoryQuery,mPageSize, mPageIndex).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<String> result) {
                        JLog.json(result.getResults());
                    }
                });
    }
}
