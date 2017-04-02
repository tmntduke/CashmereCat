package com.jh.cashmerecat.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jh.cashmerecat.Fragment.CategoryArticlFragment;
import com.jh.cashmerecat.Fragment.NewsFragment;
import com.jh.cashmerecat.Model.CategoryArticlInfo;
import com.jh.cashmerecat.Model.CategoryInfo;

import java.util.List;

/**
 * 资讯页面滑动适配器
 * Created by tmnt on 2016/12/1.
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {

    private List<CategoryInfo> mCateGoryId;


    public NewsPagerAdapter(FragmentManager fm, List<CategoryInfo> cateGoryId) {
        super(fm);
        this.mCateGoryId = cateGoryId;

    }

    @Override
    public Fragment getItem(int position) {
        return CategoryArticlFragment.getInstance(mCateGoryId.get(position).getCategoryID());
    }

    @Override
    public int getCount() {
        return mCateGoryId.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCateGoryId.get(position).getCategory_name();
    }
}
