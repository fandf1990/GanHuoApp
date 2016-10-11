package io.gank.feng24k.app.model.presentation.search;

import com.jiongbull.jlog.JLog;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.http.HttpSubscriber;
import io.gank.feng24k.app.http.apiService.SearchService;
import io.gank.feng24k.app.http.retrofit.SearchRetrofitService;
import io.gank.feng24k.app.model.itemModel.SearchItemPresentationModel;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;
import io.gank.feng24k.app.ui.activity.SearchActivity;
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
public class SearchPresentationModel extends BasePresentationModel {


    private SearchActivity mActivity;
    private int mSelectedSourceIndex;
    private String mCategoryQuery = "android";
    private String mCategoryType = "all";
    private int mPageSize = 10;
    private int mPageIndex = 1;

    public SearchPresentationModel(SearchActivity activity) {
        this.mActivity = activity;
    }

    public void setSelectedSourceIndex(int selectedSourceIndex) {
        mCategoryType = mActivity.getResources().getStringArray(R.array.languages)[selectedSourceIndex];
        JLog.d("SearchPresentationModel", mCategoryType);
    }

    public int getSelectedSourceIndex() {
        return mSelectedSourceIndex;
    }

    public void onBackClick() {
        mActivity.finish();
    }

    public String getCategoryQuery() {
        return mCategoryQuery;
    }

    public void setCategoryQuery(String categoryQuery) {
        mCategoryQuery = categoryQuery;
    }

    public void startSearchByCategory() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Call<String> call = SearchRetrofitService.getInstance().create(SearchService.class)
                        .searchByQuery(mCategoryQuery);
                try {
                    Response<String> response = call.execute();
                    if (response.code() == 200) {
                        subscriber.onNext(response.body());
                    } else {
                        subscriber.onError(new IOException());
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpSubscriber<String>() {
            @Override
            public void onFailer(Throwable e) {
                JLog.d("onFailer ",e.getMessage());
            }

            @Override
            public void onSuccess(String s) {
                JLog.d("SearchPresentation",s);
            }
        });
    }

    @ItemPresentationModel(value = SearchItemPresentationModel.class)
    public List<String> getSearchRecyclerSource() {
        return new ArrayList<>();
    }
}
