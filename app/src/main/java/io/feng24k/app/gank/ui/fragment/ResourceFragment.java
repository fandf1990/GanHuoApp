package io.feng24k.app.gank.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.main.ResourcePresentationModel;

public class ResourceFragment extends CategoryFragment{

    public static ResourceFragment newInstance(String categoryType) {
        ResourceFragment fragment = new ResourceFragment();
        Bundle bundle  = new Bundle();
        bundle.putString(FRAGMENT_CATEGORYTYPE_CODE,categoryType);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoryPresentationModel = new ResourcePresentationModel(this);
        return  createView(R.layout.resource_frag_layout,mCategoryPresentationModel);
    }

}
