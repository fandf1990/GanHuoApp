package io.gank.feng24k.app.model.presentation.main;

import android.content.Intent;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.List;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.itemModel.FuLiItemPresentationModel;
import io.gank.feng24k.app.ui.activity.PhotoViewActivity;
import io.gank.feng24k.app.ui.fragment.FuLiFragment;


@PresentationModel
public class FuLiPresentationModel extends CategoryPresentationModel  {

    public FuLiPresentationModel(FuLiFragment fragment, String categoryType ) {
        super(fragment,categoryType);
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
        intent.putExtra(PhotoViewActivity.INTENT_PHOTOVIEW_PHOTO_CODE, entity);
        mCategoryFragment.getActivity().startActivity(intent);
    }
}
