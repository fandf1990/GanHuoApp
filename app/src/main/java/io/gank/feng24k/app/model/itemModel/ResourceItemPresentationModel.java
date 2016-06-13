package io.gank.feng24k.app.model.itemModel;


import org.robobinding.itempresentationmodel.ItemContext;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.presentation.main.ResourcePresentationModel;

public class ResourceItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<BenefitEntity> {

    private BenefitEntity mBenefitEntity;
    private int position = 0;
    private ResourcePresentationModel mMainPresentationModel;


    public ResourceItemPresentationModel(ResourcePresentationModel model) {
        this.mMainPresentationModel = model;
    }

    @Override
    public void updateData(BenefitEntity item, ItemContext itemContext) {
        mBenefitEntity = item;
        position = itemContext.getPosition();
    }

    public String getTitle() {
        return mBenefitEntity.getDesc();
    }

    public String getAuthor() {
        return "感谢 "+mBenefitEntity.getWho()+" 提交";
    }

    public String getTag() {
        return mBenefitEntity.getType();
    }

    public String getTime() {
        String time = mBenefitEntity.getPublishedAt();
        time = time.substring(0, time.indexOf("T"));
        return time;
    }

    public void itemOnClick() {
        mMainPresentationModel.itemOnClick(position, mBenefitEntity);
    }

}
