package io.gank.feng24k.app.model.presentation.main;

import android.content.Intent;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.List;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.itemModel.ResourceItemPresentationModel;
import io.gank.feng24k.app.ui.activity.HistoryContentDetailActivity;
import io.gank.feng24k.app.ui.fragment.ResourceFragment;


@PresentationModel
public class ResourcePresentationModel extends CategoryPresentationModel  {

    public ResourcePresentationModel(ResourceFragment fragment) {
        super(fragment);
    }


    @ItemPresentationModel(value = ResourceItemPresentationModel.class ,factoryMethod = "createResourceItemPresentationModel")
    public List<BenefitEntity> getHomeRecyclerSource() {
        return mRecyclerSource;
    }


    public ResourceItemPresentationModel createResourceItemPresentationModel() {
        return new ResourceItemPresentationModel(this);
    }

    public void itemOnClick(int position, BenefitEntity entity) {
        Intent intent = new Intent(mCategoryFragment.getActivity(), HistoryContentDetailActivity.class);
        intent.putExtra(HistoryContentDetailActivity.INTENT_CONTENT_CODE, entity.getUrl());
        mCategoryFragment.getActivity().startActivity(intent);
    }
}
