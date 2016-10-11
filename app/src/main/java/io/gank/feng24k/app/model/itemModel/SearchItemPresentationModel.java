package io.gank.feng24k.app.model.itemModel;

import org.robobinding.itempresentationmodel.ItemContext;

import io.gank.feng24k.app.model.entity.SearchResultInfo;
import io.gank.feng24k.app.model.presentation.search.SearchPresentationModel;

/**
 * Author: fandf
 * Create Date: 2016/10/11.
 * Email: fandf@525happy.cn
 */

public class SearchItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<SearchResultInfo> {
    private String mTitle;
    private String mAuthor;
    private String mTag;
    private String mUrl;
    private int mPosition;

    private SearchPresentationModel mModel;

    public SearchItemPresentationModel(SearchPresentationModel viewModel) {
        this.mModel = viewModel;
    }

    @Override
    public void updateData(SearchResultInfo s, ItemContext context) {
        this.mTitle = s.getDesc();
        this.mAuthor = s.getWho();
        this.mTag = s.getType();
        this.mUrl = s.getUrl();
        this.mPosition = context.getPosition();
    }

    public void itemOnClick() {
        mModel.itemOnClick(mPosition);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTag() {
        return mTag;
    }

    public String getUrl() {
        return mUrl;
    }
}
