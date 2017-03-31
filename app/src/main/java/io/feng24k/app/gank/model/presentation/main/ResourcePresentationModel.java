package io.feng24k.app.gank.model.presentation.main;

import android.content.Intent;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.List;

import io.feng24k.app.gank.model.entity.BenefitEntity;
import io.feng24k.app.gank.model.itemModel.ResourceItemPresentationModel;
import io.feng24k.app.gank.ui.activity.ResourceDetailActivity;
import io.feng24k.app.gank.ui.fragment.ResourceFragment;


@PresentationModel
public class ResourcePresentationModel extends CategoryPresentationModel {

    public ResourcePresentationModel(ResourceFragment fragment) {
        super(fragment);
        mPageSize = 10;
    }


    @ItemPresentationModel(value = ResourceItemPresentationModel.class, factoryMethod = "createResourceItemPresentationModel")
    public List<BenefitEntity> getHomeRecyclerSource() {
        return mRecyclerSource;
    }


    public ResourceItemPresentationModel createResourceItemPresentationModel() {
        return new ResourceItemPresentationModel(this);
    }

    public void itemOnClick(int position, BenefitEntity entity) {
        Intent intent = new Intent(mCategoryFragment.getActivity(), ResourceDetailActivity.class);
        intent.putExtra(ResourceDetailActivity.INTENT_RESOURCE_DETAIL_CODE, entity.getUrl());
        mCategoryFragment.getActivity().startActivity(intent);
    }
}
