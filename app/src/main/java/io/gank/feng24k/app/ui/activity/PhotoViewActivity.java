package io.gank.feng24k.app.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
    private String mPhotoUrl;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoUrl = getIntent().getStringExtra(INTENT_PHOTOVIEW_PHOTO_CODE);
        mPhotoViewPrestationModel = new PhotoViewPrestationModel(this,mPhotoUrl);
        setContentView(R.layout.photo_view_activity, mPhotoViewPrestationModel);
    }

    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
        initProgressDialog();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("查看大图");
        bar.setTitleTextColor(Color.WHITE);
    }


    private void initProgressDialog(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setTitle("下载");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "取消",mPhotoViewPrestationModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.titlebar_download_item) {
            mPhotoViewPrestationModel.startDownloadPhoto();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public ProgressDialog getProgressDialog(){
        if(mProgressDialog ==null){
            initProgressDialog();
        }
        return mProgressDialog;
    }


    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(new PhotoImageViewBinding().extend(PhotoImageView.class));

    }
}
