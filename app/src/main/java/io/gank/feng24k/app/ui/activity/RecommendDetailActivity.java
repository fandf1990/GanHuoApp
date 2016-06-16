package io.gank.feng24k.app.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.model.entity.RecommendInfo;
import io.gank.feng24k.app.model.entity.RecommendItemInfo;
import io.gank.feng24k.app.ui.base.BaseMultiStateViewActivity;
import io.gank.feng24k.app.ui.widget.GlideImageView;

public class RecommendDetailActivity extends BaseMultiStateViewActivity {

    public static final String INTENT_CONTENT_CODE = "intent_content_code";
    private RecommendInfo mRecommendInfo;
    private GlideImageView mGlideImageView;
    private LinearLayout mParentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecommendInfo = getIntent().getParcelableExtra(INTENT_CONTENT_CODE);
        setContentView(R.layout.recommend_detail_activity, null);
        mGlideImageView = (GlideImageView) findViewById(R.id.recommend_detail_imageview);
        mParentLayout = (LinearLayout) findViewById(R.id.recommend_detail_parent_layout);
        createChildView();
    }


    private void createChildView() {
        mGlideImageView.setImageUrl(mRecommendInfo.getPhotoUrl());
        List<RecommendItemInfo> recommendItemInfos = mRecommendInfo.getRecommendItemInfos();
        mParentLayout.removeAllViews();
        for (RecommendItemInfo recommendItemInfo : recommendItemInfos) {
            View view = getLayoutInflater().inflate(R.layout.recommend_detail_item_layout, null);
            TextView titleTv = (TextView) view.findViewById(R.id.recommend_detail_item_title_tv);
            titleTv.setText(recommendItemInfo.getCytagoryName());
            LinearLayout rootView = (LinearLayout) view.findViewById(R.id.recommend_detail_item_rootview);
            List<RecommendItemInfo.BaseItemInfo> baseItemInfos = recommendItemInfo.getBaseItemInfos();
            rootView.removeAllViews();
            for (RecommendItemInfo.BaseItemInfo baseItemInfo : baseItemInfos) {
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.recommend_detail_item_child_layout, null);
                textView.setText(baseItemInfo.getText());
                textView.setTag(baseItemInfo.getUrl());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RecommendDetailActivity.this, ResourceDetailActivity.class);
                        intent.putExtra(ResourceDetailActivity.INTENT_RESOURCE_DETAIL_CODE, v.getTag().toString());
                        startActivity(intent);
                    }
                });
                rootView.addView(textView);
            }
            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //layoutParams.setMargins(0, 10, 0, 0);
            mParentLayout.addView(view);
        }

    }


    @Override
    protected void initView() {
        super.initView();
        showNavigationButton();
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("每日推荐");
        bar.setTitleTextColor(Color.WHITE);
    }


}
