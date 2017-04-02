package com.jh.cashmerecat.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jh.cashmerecat.Model.CategoryArticlInfo;
import com.jh.cashmerecat.R;
import com.squareup.picasso.Picasso;

/**
 * 文章列表view holder
 * Created by tmnt on 2016/12/1.
 */
public class CategoryArticleViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImgBack;
    public ImageView mImgLable;
    public ImageView mImgIcon;
    public ImageView mImgSupport;
    public ImageView mImgHalve;
    public ImageView mImgComment;
    public ImageView mImgCollect;
    public TextView mTvTitle;
    public TextView mTvSummary;
    public TextView mTvUsername;
    public TextView mTvDate;
    public View mView;
    public Context mContext;

    public CategoryArticleViewHolder(View itemView,
                                     ImageView mImgBack,
                                     ImageView mImgLable,
                                     ImageView mImgIcon,
                                     ImageView mImgSupport,
                                     ImageView mImgHalve,
                                     ImageView mImgComment,
                                     ImageView mImgCollect,
                                     TextView mTvTitle,
                                     TextView mTvSummary,
                                     TextView mTvUsername,
                                     TextView mTvDate,
                                     Context context) {
        super(itemView);
        this.mView = itemView;
        this.mImgBack = mImgBack;
        this.mImgLable = mImgLable;
        this.mImgIcon = mImgIcon;
        this.mImgSupport = mImgSupport;
        this.mImgHalve = mImgHalve;
        this.mImgComment = mImgComment;
        this.mImgCollect = mImgCollect;
        this.mTvTitle = mTvTitle;
        this.mTvSummary = mTvSummary;
        this.mTvUsername = mTvUsername;
        this.mTvDate = mTvDate;
        this.mContext = context;

    }

    /**
     * 获取viewHolder实例
     *
     * @param view
     * @return
     */
    public static CategoryArticleViewHolder getInstance(View view, Context context) {
        ImageView mImgBack = (ImageView) view.findViewById(R.id.img_bg);
        ImageView mImgLable = (ImageView) view.findViewById(R.id.img_category);
        ImageView mImgIcon = (ImageView) view.findViewById(R.id.img_icon);
        ImageView mImgSupport = (ImageView) view.findViewById(R.id.img_support);
        ImageView mImgHalve = (ImageView) view.findViewById(R.id.img_halve);
        ImageView mImgComment = (ImageView) view.findViewById(R.id.img_comment);
        ImageView mImgCollect = (ImageView) view.findViewById(R.id.img_collect);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mTvSummary = (TextView) view.findViewById(R.id.tv_summary);
        TextView mTvUsername = (TextView) view.findViewById(R.id.tv_username);
        TextView mTvDate = (TextView) view.findViewById(R.id.tv_date);

        return new CategoryArticleViewHolder(view
                , mImgBack
                , mImgLable
                , mImgIcon
                , mImgSupport
                , mImgHalve
                , mImgComment
                , mImgCollect
                , mTvTitle
                , mTvSummary
                , mTvUsername
                , mTvDate
                , context);
    }

    /**
     * 设置数据
     *
     * @param info
     */
    public void setData(CategoryArticlInfo info) {
        Picasso.with(mContext).load(info.getBg_image()).into(mImgBack);
        Picasso.with(mContext).load(info.getLabel()).into(mImgLable);
        Picasso.with(mContext).load(info.getIcon()).into(mImgIcon);
        mTvTitle.setText(info.getTitle());
        mTvSummary.setText(info.getSummary());
        mTvUsername.setText(info.getUsername());
        mTvDate.setText(info.getDate());

    }


}
