package io.gank.feng24k.app.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;
import io.gank.feng24k.app.ui.fragment.FuLiFragment;
import io.gank.feng24k.app.ui.fragment.RecommendFragment;
import io.gank.feng24k.app.ui.fragment.ResourceFragment;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    private final String[] mCategoryArrays = {"推荐", "Android", "iOS","福利", "休息视频", "拓展资源", "前端", "all"};
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, null);
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initView() {
        super.initView();
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mSmartTabLayout = (SmartTabLayout) findViewById(R.id.main_viewpager_tab_layout);
        mFragmentList.add(RecommendFragment.newInstance("每日推荐"));
        mFragmentList.add(ResourceFragment.newInstance("Android"));
        mFragmentList.add(ResourceFragment.newInstance("iOS"));
        mFragmentList.add(FuLiFragment.newInstance("福利"));
        mFragmentList.add(ResourceFragment.newInstance("休息视频"));
        mFragmentList.add(ResourceFragment.newInstance("拓展资源"));
        mFragmentList.add(ResourceFragment.newInstance("前端"));
        mFragmentList.add(ResourceFragment.newInstance("all"));

        mCategoryPagerAdapter = new CategoryPagerAdapter();
        mViewPager.setAdapter(mCategoryPagerAdapter);

        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("Gank.io");
        bar.setTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.titlebar_search) {
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class CategoryPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{

        private int currentPageIndex = 0; // 当前page索引（切换之前）
        private OnExtraPageChangeListener onExtraPageChangeListener; // ViewPager切换页面时的额外功能添加接口

        public CategoryPagerAdapter() {
            mViewPager.setOnPageChangeListener(this);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mFragmentList.get(position).getView()); // 移出viewpager两边之外的page布局
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = mFragmentList.get(position);
            if(!fragment.isAdded()){ // 如果fragment还没有added
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.add(fragment, fragment.getClass().getSimpleName());
                ft.commitAllowingStateLoss();
                /**
                 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
                 * 会在进程的主线程中，用异步的方式来执行。
                 * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
                 * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
                 */
                mFragmentManager.executePendingTransactions();
            }

            if(fragment.getView().getParent() == null){
                container.addView(fragment.getView()); // 为viewpager增加布局
            }

            return fragment.getView();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mCategoryArrays[position];
        }



        /**
         * 当前page索引（切换之前）
         * @return
         */
        public int getCurrentPageIndex() {
            return currentPageIndex;
        }

        public OnExtraPageChangeListener getOnExtraPageChangeListener() {
            return onExtraPageChangeListener;
        }

        /**
         * 设置页面切换额外功能监听器
         * @param onExtraPageChangeListener
         */
        public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
            this.onExtraPageChangeListener = onExtraPageChangeListener;
        }

        @Override
        public void onPageScrolled(int i, float v, int i2) {
            if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
                onExtraPageChangeListener.onExtraPageScrolled(i, v, i2);
            }
        }

        @Override
        public void onPageSelected(int i) {
            mFragmentList.get(currentPageIndex).onPause(); // 调用切换前Fargment的onPause()
//        fragments.get(currentPageIndex).onStop(); // 调用切换前Fargment的onStop()
            if(mFragmentList.get(i).isAdded()){
//            fragments.get(i).onStart(); // 调用切换后Fargment的onStart()
                mFragmentList.get(i).onResume(); // 调用切换后Fargment的onResume()
            }
            currentPageIndex = i;

            if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
                onExtraPageChangeListener.onExtraPageSelected(i);
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if(null != onExtraPageChangeListener){ // 如果设置了额外功能接口
                onExtraPageChangeListener.onExtraPageScrollStateChanged(i);
            }
        }


        /**
         * page切换额外功能接口
         */
        public  class OnExtraPageChangeListener{
            public void onExtraPageScrolled(int i, float v, int i2){}
            public void onExtraPageSelected(int i){}
            public void onExtraPageScrollStateChanged(int i){}
        }



    }



}
