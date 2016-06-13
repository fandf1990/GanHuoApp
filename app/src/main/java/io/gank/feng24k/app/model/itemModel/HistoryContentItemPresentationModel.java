package io.gank.feng24k.app.model.itemModel;


import org.robobinding.itempresentationmodel.ItemContext;

import io.gank.feng24k.app.model.entity.HistoryContentInfo;
import io.gank.feng24k.app.model.presentation.main.HistoryContentPresentationModel;

public class HistoryContentItemPresentationModel  implements org.robobinding.itempresentationmodel.ItemPresentationModel<HistoryContentInfo> {

    private HistoryContentPresentationModel mModel;
    private HistoryContentInfo mHistoryContentInfo;
    private int position = 0;


    public HistoryContentItemPresentationModel(HistoryContentPresentationModel model) {
        this.mModel = model;
    }

    @Override
    public void updateData(HistoryContentInfo historyContentInfo, ItemContext itemContext) {
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

    public String getTime(){
        String time = mHistoryContentInfo.getPublishedAt();
        time = time.substring(0,time.indexOf("T"));
        return time;
    }
}
