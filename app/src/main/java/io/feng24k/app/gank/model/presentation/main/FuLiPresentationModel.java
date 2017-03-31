package io.feng24k.app.gank.model.presentation.main;

import android.content.Intent;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.List;

import io.feng24k.app.gank.model.entity.BenefitEntity;
import io.feng24k.app.gank.model.itemModel.FuLiItemPresentationModel;
import io.feng24k.app.gank.ui.activity.PhotoViewActivity;
import io.feng24k.app.gank.ui.fragment.FuLiFragment;


@PresentationModel
public class FuLiPresentationModel extends CategoryPresentationModel  {

    public FuLiPresentationModel(FuLiFragment fragment) {
        super(fragment);
    }


    @ItemPresentationModel(value = FuLiItemPresentationModel.class ,factoryMethod = "createFuLiItemPresentationModel")
    public List<BenefitEntity> getHomeRecyclerSource() {
        return mRecyclerSource;
    }


    public FuLiItemPresentationModel createFuLiItemPresentationModel() {
        return new FuLiItemPresentationModel(this);
    }

    public void itemOnClick(int position, BenefitEntity entity) {
        Intent intent = new Intent(mCategoryFragment.getActivity(), PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.INTENT_PHOTOVIEW_PHOTO_CODE, entity.getUrl());
        mCategoryFragment.getActivity().startActivity(intent);
    }
}
