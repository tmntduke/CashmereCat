package com.jh.cashmerecat.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.jh.cashmerecat.Fragment.LoginFragment;
import com.jh.cashmerecat.Fragment.RegisterFragment;
import com.jh.cashmerecat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 用户操作activity（用户登录和注册）
 * Created by tmnt on 2016/11/28.
 */
public class UserOprationActivity extends BaseActivity {


    @Bind(R.id.btn_op_login)
    TextView mBtnOpLogin;//登录按钮
    @Bind(R.id.btn_op_register)
    TextView mBtnOpRegister;//注册按钮

    private FragmentManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useropration);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_contain, new LoginFragment()).commit();

        initOperation();
    }

    @Override
    public void initOperation() {

        mBtnOpLogin.setOnClickListener(v ->
                manager.beginTransaction().replace(R.id.fl_contain, new LoginFragment()).commit()
        );

        mBtnOpRegister.setOnClickListener(v ->
                manager.beginTransaction().replace(R.id.fl_contain, RegisterFragment.getInstance()).commit()
        );

    }

    @Override
    public void loadData() {

    }

}
