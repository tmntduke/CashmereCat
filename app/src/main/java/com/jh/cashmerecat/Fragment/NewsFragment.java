package com.jh.cashmerecat.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jh.cashmerecat.Adapter.NewsPagerAdapter;
import com.jh.cashmerecat.Detail.UserData;
import com.jh.cashmerecat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 资讯界面
 * <p>
 * Created by tmnt on 2016/12/1.
 */
public class NewsFragment extends BaseFragment {

    public static final String CATEGORY_ID = "ARTICL_ID";
    @Bind(R.id.tablayout)
    TabLayout mTablayout;//标题栏
    @Bind(R.id.img_person)
    ImageView mImgPerson;//个人中心按钮
    @Bind(R.id.etSearch)
    TextView mEtSearch;//搜索框
    @Bind(R.id.showView)
    ViewPager mShowView;//显示文章列表

    private String categoryId;//文章分类id

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        initOperation();

        return view;
    }

    /**
     * 获取fragment实例
     *
     * @return
     */
    public static Fragment getInstance(String categoryId) {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initOperation() {
        int size = UserData.getCategory(false).size();
        for (int i = 0; i <= size - 1; i++) {
            mTablayout.addTab(mTablayout.newTab().setText(UserData.getCategory(false).get(i).getCategory_name()));
        }

        FragmentManager manager = getChildFragmentManager();
        NewsPagerAdapter myAdapter = new NewsPagerAdapter(manager, UserData.getCategory(false));
        mShowView.setAdapter(myAdapter);
        mTablayout.setupWithViewPager(mShowView);
        mTablayout.setTabsFromPagerAdapter(myAdapter);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    //调webservice
}
