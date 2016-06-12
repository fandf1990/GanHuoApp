package io.gank.feng24k.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;
import io.gank.feng24k.app.ui.fragment.CategoryFragment;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    private final String[] mCategoryArrays = {"福利", "Android", "iOS", "休息视频", "拓展资源", "前端", "all"};
    private ViewPager mViewPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, null);
    }

    @Override
    protected void initView() {
        super.initView();
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mFragmentList.add(CategoryFragment.newInstance("福利"));
        mFragmentList.add(CategoryFragment.newInstance("福利"));
        mFragmentList.add(CategoryFragment.newInstance("福利"));
        mFragmentList.add(CategoryFragment.newInstance("福利"));
        mFragmentList.add(CategoryFragment.newInstance("福利"));

        mCategoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCategoryPagerAdapter);
    }

    @Override
    protected void setToolBarTitle(Toolbar bar) {
        super.setToolBarTitle(bar);
        bar.setTitle("Gank.io福利");
        bar.setTitleTextColor(Color.WHITE);
    }


    private class CategoryPagerAdapter extends FragmentStatePagerAdapter {


        public CategoryPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup arg0, int arg1) {

            return super.instantiateItem(arg0, arg1);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            super.destroyItem(container, position, object);
        }

    }


}
