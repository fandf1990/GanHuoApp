package io.feng24k.app.gank.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import org.robobinding.binder.BinderFactoryBuilder;

import io.feng24k.app.gank.R;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutId, Object presentationModel) {
        LinearLayout rootView = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.base_activity_layout, null);
        addChildView(rootView, layoutId, presentationModel);
        setContentView(rootView);
        initView();
    }

    protected void initView() {

    }


    private void addChildView(View rootView, int layoutId, Object presentationModel) {
        BinderFactoryBuilder builder = new BinderFactoryBuilder();
        preBindView(builder);
        View childView;
        if (presentationModel != null) {
            childView = builder.build().createViewBinder(this).inflateAndBind(layoutId, presentationModel);
            childView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT));

        } else {
            childView = LayoutInflater.from(this).inflate(layoutId, null);
        }
        ((LinearLayout) rootView
                .findViewById(R.id.base_activity_parent_layout)).addView(childView);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setToolBarTitle(toolbar);
        setSupportActionBar(toolbar);
    }

    protected void preBindView(BinderFactoryBuilder builder) {

    }


    protected void setToolBarTitle(Toolbar bar) {

    }

    protected void showNavigationButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
