package com.jh.cashmerecat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jh.cashmerecat.Model.CategoryArticlInfo;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.ViewHolder.CategoryArticleViewHolder;

import java.util.List;

/**
 * 文章列表适配器
 * Created by tmnt on 2016/12/1.
 */
public class CategoryArtcileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CategoryArticlInfo> mArticlInfos;
    private Context mContext;

    private OnClickListener mOnClickListener;

    private static final String TAG = "CategoryArtcileAdapter";

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public CategoryArtcileAdapter(List<CategoryArticlInfo> articlInfos, Context context) {
        mArticlInfos = articlInfos;
        mContext = context;
        Log.i(TAG, "CategoryArtcileAdapter: start");
        Log.i(TAG, "CategoryArtcileAdapter: "+mArticlInfos.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: start");
        View view = LayoutInflater.from(mContext).inflate(R.layout.listview_news_item, parent, false);
        return CategoryArticleViewHolder.getInstance(view, mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: " + mArticlInfos.size());

        CategoryArticleViewHolder viewHolder = (CategoryArticleViewHolder) holder;
        viewHolder.setData(mArticlInfos.get(position));

        viewHolder.mImgIcon.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onIcon(v);
            }
        });

        viewHolder.mImgSupport.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onSupport(v);
            }
        });

        viewHolder.mImgCollect.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onCollect(v);
            }
        });

        viewHolder.mImgHalve.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onHavle(v);
            }
        });

        viewHolder.mImgComment.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onComment(v);
            }
        });

        viewHolder.mImgBack.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onArticle(v);
            }
        });

        viewHolder.mTvTitle.setOnClickListener(v -> {
            if (mOnClickListener != null) {
                mOnClickListener.onArticle(v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    /**
     * 控件点击事件
     */
    public interface OnClickListener {
        void onIcon(View view);

        void onSupport(View view);

        void onHavle(View view);

        void onCollect(View view);

        void onComment(View view);

        void onArticle(View view);
    }

}
