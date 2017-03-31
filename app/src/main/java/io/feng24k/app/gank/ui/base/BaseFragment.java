package io.feng24k.app.gank.ui.base;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.kennyc.view.MultiStateView;

import org.robobinding.binder.BinderFactoryBuilder;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.BasePresentationModel;

public class BaseFragment extends Fragment {

    private View mView;
    private MultiStateView mMultiStateView;

    public View createView(int layoutId, BasePresentationModel presentationModel) {
        BinderFactoryBuilder builder = new BinderFactoryBuilder();
        preBindView(builder);
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.base_fragment_layout, null);
        if (presentationModel != null) {
            mView = builder.build().createViewBinder(getActivity()).inflateAndBind(layoutId, presentationModel);
            mView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        } else {
            mView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
        }
        ((LinearLayout) rootView
                .findViewById(R.id.base_fragment_parent_layout)).addView(mView);
        mMultiStateView = (MultiStateView) rootView.findViewById(R.id.multiview_layout_fragment);
        return rootView;
    }

    protected void preBindView(BinderFactoryBuilder builder) {

    }

    protected View findViewById(int viewId) {
        return mView.findViewById(viewId);
    }

    public void setMultiViewState(int multiViewState) {
        if (mMultiStateView != null) {
            mMultiStateView.setViewState(multiViewState);
        }
    }

    public void setErrorViewClickListener(View.OnClickListener onClickListener) {
        if (mMultiStateView != null && mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR) != null) {
            mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).setOnClickListener(onClickListener);
        }
    }

}
