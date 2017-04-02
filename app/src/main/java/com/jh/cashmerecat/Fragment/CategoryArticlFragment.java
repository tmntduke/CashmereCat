package com.jh.cashmerecat.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jh.cashmerecat.Activity.PublicArticleActivity;
import com.jh.cashmerecat.Adapter.CategoryArtcileAdapter;
import com.jh.cashmerecat.Detail.UserData;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 文章列表
 * Created by tmnt on 2016/12/1.
 */
public class CategoryArticlFragment extends Fragment {

    private static String CATEGORY_ID = "CATEGORY_ID";

    @Bind(R.id.swipe_target)
    RecyclerView mRvArticle;//文章列表
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    private String categoryId;

    private static final String TAG = "CategoryArticlFragment";

    private CategoryArtcileAdapter mArtcileAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryId = (String) getArguments().getSerializable(CATEGORY_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_articl, container, false);

        ButterKnife.bind(this, view);

        initOpration();
        //autoRefresh();
        return view;
    }

    /**
     * 获取fragment实例
     *
     * @return
     */
    public static Fragment getInstance(String categoryId) {
        CategoryArticlFragment fragment = new CategoryArticlFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 控件操作
     */
    private void initOpration() {
        mArtcileAdapter = new CategoryArtcileAdapter(UserData.getArticlList(), getActivity());
        mRvArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvArticle.setAdapter(mArtcileAdapter);

        mArtcileAdapter.setOnClickListener(new CategoryArtcileAdapter.OnClickListener() {
            @Override
            public void onIcon(View view) {
                Utils.showToast(getActivity(), "icon");
            }

            @Override
            public void onSupport(View view) {
                Utils.showToast(getActivity(), "support");
            }

            @Override
            public void onHavle(View view) {
                Utils.showToast(getActivity(), "halve");
            }

            @Override
            public void onCollect(View view) {
                Utils.showToast(getActivity(), "collect");
            }

            @Override
            public void onComment(View view) {
                Utils.showToast(getActivity(), "comment");
            }

            @Override
            public void onArticle(View view) {
                Intent intent = new Intent(getActivity(), PublicArticleActivity.class);
                startActivity(intent);
            }
        });

        mRvArticle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        mSwipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });

        mSwipeToLoadLayout.setOnLoadMoreListener(() ->
                mSwipeToLoadLayout.postDelayed(() -> {
                    Log.i(TAG, "initOpration: start");
                    if (mSwipeToLoadLayout.isLoadingMore()) {
                        mSwipeToLoadLayout.setLoadingMore(false);
                    }
                }, 2000)
        );


        mSwipeToLoadLayout.setOnRefreshListener(() ->
                mSwipeToLoadLayout.postDelayed(() -> {
                    Log.i(TAG, "initOpration: start");
                    if (mSwipeToLoadLayout.isRefreshing()) {
                        mSwipeToLoadLayout.setRefreshing(false);
                    }
                }, 2000)
        );


    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSwipeToLoadLayout.isRefreshing()) {
            mSwipeToLoadLayout.setRefreshing(false);
        }
        if (mSwipeToLoadLayout.isLoadingMore()) {
            mSwipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

//调web service

