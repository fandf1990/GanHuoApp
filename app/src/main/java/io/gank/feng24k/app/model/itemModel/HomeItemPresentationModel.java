package io.gank.feng24k.app.model.itemModel;


import org.robobinding.itempresentationmodel.ItemContext;

import io.gank.feng24k.app.model.entity.BenefitEntity;

public class HomeItemPresentationModel implements org.robobinding.itempresentationmodel.ItemPresentationModel<BenefitEntity> {
    private String name;
    private String url;

    @Override
    public void updateData(BenefitEntity item, ItemContext itemContext) {
        this.name = item.getWho();
        this.url = item.getUrl();
    }

    public String getWho() {
        return name;
    }

    public String getImageUrl(){
        return url;
    }
}
