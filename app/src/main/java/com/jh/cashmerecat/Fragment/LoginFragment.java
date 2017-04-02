package com.jh.cashmerecat.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jh.cashmerecat.Activity.MainActivity;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ShareOpration;
import com.jh.cashmerecat.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * 用户登录界面
 * Created by tmnt on 2016/11/28.
 */
public class LoginFragment extends BaseFragment {

    @Bind(R.id.img_up)
    ImageView mImgUp;
    @Bind(R.id.ed_phone)
    EditText mEdPhone;
    @Bind(R.id.ed_password)
    EditText mEdPassword;
    @Bind(R.id.tv_forgetPassword)
    TextView mTvForgetPassword;
    @Bind(R.id.bt_login)
    Button mBtLogin;
    @Bind(R.id.img_wechat)
    ImageView mImgWechat;
    @Bind(R.id.img_weibo)
    ImageView mImgWeibo;
    @Bind(R.id.img_qq)
    ImageView mImgQq;

    private static final int MSG_LOGIN = 2;

    private static final String TAG = "LoginFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        initOperation();


        return view;
    }


    @Override
    public void initOperation() {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mImgUp.getLayoutParams();
        layoutParams.leftMargin = Utils.getScreenWidth(getActivity()) / 5;
        mImgUp.setLayoutParams(layoutParams);

        mEdPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String phoneText;
                if (!hasFocus) {
                    phoneText = mEdPhone.getText().toString();
                    if (phoneText.length() == 0) {
                        mEdPhone.setError("手机号不能为空");
                    } else if (phoneText.length() < 11) {
                        mEdPhone.setError("请输入正确手机号");
                    }
                }
            }
        });


        mEdPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String passwordText;
                if (!hasFocus) {
                    passwordText = mEdPassword.getText().toString();
                    if (passwordText.length() == 0) {
                        mEdPassword.setError("密码不能为空");
                    } else if (passwordText.length() <= 6 && passwordText.length() >= 15) {
                        mEdPassword.setError("请输入6-15位密码");
                    }
                }
            }
        });

        //登录按钮
        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //测试
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        //忘记密码
        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                startActivity(inteit);
            }
        });

        //微信登录
        mImgWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(Wechat.NAME);
            }
        });

        //微博登录
        mImgWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(SinaWeibo.NAME);
            }
        });

        //QQ登录
        mImgQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(QQ.NAME);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 授权验证
     */
    public static class MyHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 101) {
                Log.i(TAG, "handleMessage: 授权");

            }

        }
    }

}
