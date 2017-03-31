package io.feng24k.app.gank.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.main.FuLiPresentationModel;

public class FuLiFragment extends CategoryFragment{

    public static FuLiFragment newInstance(String categoryType) {
        FuLiFragment fragment = new FuLiFragment();
        Bundle bundle  = new Bundle();
        bundle.putString(FRAGMENT_CATEGORYTYPE_CODE,categoryType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoryPresentationModel = new FuLiPresentationModel(this);
        return  createView(R.layout.fuli_frag_layout,mCategoryPresentationModel);
    }
}
