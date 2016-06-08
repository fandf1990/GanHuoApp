package io.gank.feng24k.app.model.presentation.main;

import org.robobinding.annotation.PresentationModel;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.presentation.BasePresentationModel;


@PresentationModel
public class PhotoViewPrestationModel extends BasePresentationModel {

    private BenefitEntity mBenefitEntity;

    public PhotoViewPrestationModel(BenefitEntity entity){
        this.mBenefitEntity = entity;
    }

    public String getImageUrl(){
      return  mBenefitEntity.getUrl();
    }


}
