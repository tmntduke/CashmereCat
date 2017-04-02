package com.jh.cashmerecat.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jh.cashmerecat.R;

/**
 * 选择分类控件
 * Created by tmnt on 2016/12/5.
 */
public class PickCategory extends RelativeLayout {

    private TextView category;//分类
    private ImageView add;//添加按钮

    //private String category_str;//分类名称

    private OnClickTextListener mOnClickTextListener;

    public void setOnClickTextListener(OnClickTextListener onClickTextListener) {
        mOnClickTextListener = onClickTextListener;
    }

    public PickCategory(Context context) {
        super(context);
    }

    public PickCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        category = (TextView) findViewById(R.id.category);
        add = (ImageView) findViewById(R.id.add);

        initOpration();
    }

    private void initOpration() {

        category.setOnClickListener(v -> {
            if (mOnClickTextListener != null) {
                mOnClickTextListener.onClick(v);
            }
        });

        add.setOnClickListener(v -> {
            if (mOnClickTextListener != null) {
                mOnClickTextListener.onClick(v);
            }
        });

    }

    /**
     * 添加分类名称
     *
     * @param category_str
     */
    public void setCategoryText(String category_str) {
        category.setText(category_str);
    }

    public interface OnClickTextListener {
        void onClick(View view);
    }

}
