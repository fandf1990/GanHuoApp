package io.feng24k.app.gank.model.itemModel;


import org.robobinding.itempresentationmodel.ItemContext;

import io.feng24k.app.gank.model.entity.BenefitEntity;
import io.feng24k.app.gank.model.presentation.main.FuLiPresentationModel;

public class FuLiItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<BenefitEntity> {

    private BenefitEntity mBenefitEntity;
    private int position = 0;
    private FuLiPresentationModel mMainPresentationModel;


    public FuLiItemPresentationModel(FuLiPresentationModel model) {
        this.mMainPresentationModel = model;
    }

    @Override
    public void updateData(BenefitEntity item, ItemContext itemContext) {
        mBenefitEntity = item;
        position = itemContext.getPosition();
    }

    public String getWho() {
        return mBenefitEntity.getDesc();
    }

    public String getImageUrl() {
        return mBenefitEntity.getUrl();
    }

    public void itemOnClick() {
        mMainPresentationModel.itemOnClick(position, mBenefitEntity);
    }

}
