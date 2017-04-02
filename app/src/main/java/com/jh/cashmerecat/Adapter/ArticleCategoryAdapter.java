package com.jh.cashmerecat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jh.cashmerecat.Activity.BaseActivity;
import com.jh.cashmerecat.Model.CategoryInfo;
import com.jh.cashmerecat.R;

import java.util.List;

/**
 * 分类弹框列表适配器
 * Created by tmnt on 2016/12/5.
 */
public class ArticleCategoryAdapter extends BaseAdapter {

    private List<CategoryInfo> mCategoryInfos;
    private Context mContext;

    public ArticleCategoryAdapter(List<CategoryInfo> categoryInfos, Context context) {
        mCategoryInfos = categoryInfos;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCategoryInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoryInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_category_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) view.findViewById(R.id.tv_category);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mTextView.setText(mCategoryInfos.get(position).getCategory_name());

        return view;
    }

    class ViewHolder {
        TextView mTextView;
    }
}
