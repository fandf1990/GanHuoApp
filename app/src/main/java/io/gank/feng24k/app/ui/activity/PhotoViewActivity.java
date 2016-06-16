package io.gank.feng24k.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import org.robobinding.binder.BinderFactoryBuilder;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.presentation.main.PhotoViewPrestationModel;
import io.gank.feng24k.app.ui.base.BaseMultiStateViewActivity;
import io.gank.feng24k.app.ui.widget.PhotoImageView;
import io.gank.feng24k.app.ui.widget.binding.PhotoImageViewBinding;


public class PhotoViewActivity extends BaseMultiStateViewActivity {

    public static final String INTENT_PHOTOVIEW_PHOTO_CODE = "intent_photoview_photo_code";
    private PhotoViewPrestationModel mPhotoViewPrestationModel;
    private BenefitEntity mBenefitEntity;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBenefitEntity = getIntent().getParcelableExtra(INTENT_PHOTOVIEW_PHOTO_CODE);
        mPhotoViewPrestationModel = new PhotoViewPrestationModel(mBenefitEntity);
        setContentView(R.layout.photo_view_activity, mPhotoViewPrestationModel);

    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("查看大图");
        bar.setTitleTextColor(Color.WHITE);
    }


    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(new PhotoImageViewBinding().extend(PhotoImageView.class));

    }
}
