package io.gank.feng24k.app.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.presentation.main.FuLiPresentationModel;

public class FuLiFragment extends CategoryFragment{

    public static FuLiFragment newInstance(String categoryType) {
        FuLiFragment fragment = new FuLiFragment();
        Bundle bundle  = new Bundle();
        bundle.putString(FRAGMENT_CATEGORYTYPE_CODE,categoryType);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoryPresentationModel = new FuLiPresentationModel(this);
        return  createView(R.layout.category_frag_layout,mCategoryPresentationModel);
    }
}
