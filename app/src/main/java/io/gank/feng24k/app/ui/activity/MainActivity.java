package io.gank.feng24k.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.gank.feng24k.app.R;
import io.gank.feng24k.app.ui.base.BaseActivity;
import io.gank.feng24k.app.ui.fragment.FuLiFragment;

/**
 * 首页
 */
public class MainActivity extends BaseActivity {

    private final String[] mCategoryArrays = {"福利", "Android", "iOS", "休息视频", "拓展资源", "前端", "all"};
    private ViewPager mViewPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, null);
    }

    @Override
    protected void initView() {
        super.initView();
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mSmartTabLayout = (SmartTabLayout) findViewById(R.id.main_viewpager_tab_layout);
        mFragmentList.add(FuLiFragment.newInstance("福利"));
        mFragmentList.add(FuLiFragment.newInstance("福利"));
        mFragmentList.add(FuLiFragment.newInstance("福利"));
        mFragmentList.add(FuLiFragment.newInstance("福利"));
        mFragmentList.add(FuLiFragment.newInstance("福利"));

        mCategoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager());
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

    private class CategoryPagerAdapter extends FragmentPagerAdapter {

        private  SparseArrayCompat<WeakReference<Fragment>> holder;


        public CategoryPagerAdapter(FragmentManager fm) {
            super(fm);
            this.holder = new SparseArrayCompat<>(mFragmentList.size());
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
        public Object instantiateItem(ViewGroup container, int position) {
            Object item = super.instantiateItem(container, position);
            if (item instanceof Fragment) {
                holder.put(position, new WeakReference<Fragment>((Fragment) item));
            }
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            holder.remove(position);
            super.destroyItem(container, position, object);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return mCategoryArrays[position];
        }
    }


}
