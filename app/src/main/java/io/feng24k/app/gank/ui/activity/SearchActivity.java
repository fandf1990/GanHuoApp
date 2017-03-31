package io.feng24k.app.gank.ui.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.robobinding.binder.BinderFactoryBuilder;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.supportwidget.recyclerview.RecyclerViewBinding;

import io.feng24k.app.gank.R;
import io.feng24k.app.gank.model.presentation.search.SearchPresentationModel;
import io.feng24k.app.gank.ui.base.BaseMultiStateViewActivity;

public class SearchActivity extends BaseMultiStateViewActivity implements View.OnClickListener{


    private SearchPresentationModel mSearchPresentationModel;
    private RecyclerView mRecyclerView;
    private EditText mQueryEdit;
    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchPresentationModel = new SearchPresentationModel(this);
        setContentView(R.layout.search_activity, mSearchPresentationModel);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
        mQueryEdit = (EditText) findViewById(R.id.search_query_edit);

        findViewById(R.id.search_back_imv).setOnClickListener(this);
        findViewById(R.id.search_invoke_imv).setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        setMultiStateViewListener(mSearchPresentationModel);
        mQueryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {
                    mSearchPresentationModel.resetDataEmpty();
                }
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void preBindView(BinderFactoryBuilder builder) {
        super.preBindView(builder);
        builder.add(CustomViewBinding.forView(RecyclerView.class, new RecyclerViewBinding()));
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setContentInsetStartWithNavigation(0);
        View titleView = getLayoutInflater().inflate(R.layout.search_title_layout, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        bar.addView(titleView, layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_back_imv:{
                mSearchPresentationModel.onBackClick();
                break;
            }
            case R.id.search_invoke_imv:{
                String query = mQueryEdit.getText().toString();
                if(TextUtils.isEmpty(query)){
                    Toast.makeText(this,"请输入搜索关键字",Toast.LENGTH_SHORT).show();
                    return;
                }
                dismissInputMethodDialog();
                mSearchPresentationModel.onSearchClick(query);
                break;
            }
        }
    }

    private void dismissInputMethodDialog(){
        if(mInputMethodManager==null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(mQueryEdit.getWindowToken(),0);
    }
}
