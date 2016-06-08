package io.gank.feng24k.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.presentation.main.MainPresentationModel;
import io.gank.feng24k.app.ui.base.BaseActivity;
import io.gank.feng24k.app.ui.widget.GlideImageView;
import io.gank.feng24k.app.ui.widget.binding.GlideImageBinding;
import io.gank.feng24k.app.ui.widget.binding.SwipeToLoadLayoutBinding;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    private MainPresentationModel mMainPresentationModel;
    private RecyclerView mRecyclerView;
    private SwipeToLoadLayout mSwipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainPresentationModel = new MainPresentationModel(this);
        setContentView(R.layout.activity_main, mMainPresentationModel);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.main_swipe_load_layout);
        mSwipeToLoadLayout.setOnRefreshListener(mMainPresentationModel);
        mSwipeToLoadLayout.setOnLoadMoreListener(mMainPresentationModel);
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mMainPresentationModel.autoLoadBenefit();
            }
        });
    }


    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("Gank.io福利");
        bar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(CustomViewBinding.forView(RecyclerView.class, new RecyclerViewBinding()))
                .add(new GlideImageBinding().extend(GlideImageView.class))
                .add(new SwipeToLoadLayoutBinding().extend(SwipeToLoadLayout.class));
    }
}
