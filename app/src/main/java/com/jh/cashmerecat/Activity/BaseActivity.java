package com.jh.cashmerecat.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.Utils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * activity基类 其他activity继承
 * Created by tmnt on 2016/11/28.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initOpration();
    }

    /**
     * 对控件的操作
     */
    public abstract void initOpration();


    /**
     * 设置状态栏沉浸
     *
     * @param color
     */
    public void setStatesBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Utils.setTranslucentStatus(this, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);
    }


    /**
     * 返回按钮点击事件
     *
     * @param v
     */
    public void onBack(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }

}
