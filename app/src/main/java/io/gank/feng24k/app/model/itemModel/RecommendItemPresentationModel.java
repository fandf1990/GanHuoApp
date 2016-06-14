package io.gank.feng24k.app.model.itemModel;


import org.robobinding.itempresentationmodel.ItemContext;

import io.gank.feng24k.app.model.entity.RecommendInfo;
import io.gank.feng24k.app.model.presentation.main.RecommendPresentationModel;

public class RecommendItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<RecommendInfo> {

    private RecommendPresentationModel mModel;
    private RecommendInfo mHistoryContentInfo;
    private int position = 0;


    public RecommendItemPresentationModel(RecommendPresentationModel model) {
        this.mModel = model;
    }

    @Override
    public void updateData(RecommendInfo historyContentInfo, ItemContext itemContext) {
        this.position = itemContext.getPosition();
        this.mHistoryContentInfo = historyContentInfo;
    }

    public void itemOnClick() {
        mModel.itemOnClick(position, mHistoryContentInfo);
    }

    public String getTitle(){
        return mHistoryContentInfo.getTitle();
    }
    public String getTag(){
        return "每日推荐";
    }

    public String getPhotoUrl(){
        return mHistoryContentInfo.htmlAnalysis().getPhotoUrl();
    }
    public String getTime(){
        String time = mHistoryContentInfo.getPublishedAt();
        time = time.substring(0,time.indexOf("T"));
        return time;
    }
}
