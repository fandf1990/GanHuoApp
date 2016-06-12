package io.gank.feng24k.app.ui.base;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import org.robobinding.binder.BinderFactoryBuilder;

import io.gank.feng24k.app.model.presentation.BasePresentationModel;

public class BaseFragment extends Fragment{

    private View mView;

    public View createView(int layoutId, BasePresentationModel presentationModel){
        BinderFactoryBuilder builder = new BinderFactoryBuilder();
        preBindView(builder);
        if (presentationModel != null) {
            mView = builder.build().createViewBinder(getActivity()).inflateAndBind(layoutId, presentationModel);
        } else {
            mView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
        }
        return mView;
    }

    protected void preBindView(BinderFactoryBuilder builder) {

    }

    protected View findViewById(int viewId){
        return mView.findViewById(viewId);
    }
}
