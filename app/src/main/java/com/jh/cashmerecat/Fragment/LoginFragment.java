package com.jh.cashmerecat.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.cashmerecat.Activity.MainActivity;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ShareOpration;
import com.jh.cashmerecat.Utils.Utils;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * 用户登录界面
 * Created by E540 on 2016/11/28.
 */
public class LoginFragment extends Fragment {

    private EditText ed_phone;//登录手机号
    private EditText ed_password;//登录密码
    private Button bt_login;//登录按钮
    private TextView tv_forgetPassword;//忘记密码按钮
    private ImageView img_wechat;//第三方微信登录按钮
    private ImageView img_weibo;//第三方微博登录按钮
    private ImageView img_qq;//第三方QQ登录按钮
    private ImageView img_up;

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
        initView(view);
        initOperation();

        return view;
    }


    /**
     * 控件初始化
     */

    public void initView(View view) {

        ed_phone = (EditText) view.findViewById(R.id.ed_phone);
        ed_password = (EditText) view.findViewById(R.id.ed_password);
        bt_login = (Button) view.findViewById(R.id.bt_login);
        tv_forgetPassword = (TextView) view.findViewById(R.id.tv_forgetPassword);
        img_wechat = (ImageView) view.findViewById(R.id.img_wechat);
        img_weibo = (ImageView) view.findViewById(R.id.img_weibo);
        img_qq = (ImageView) view.findViewById(R.id.img_qq);
        img_up = (ImageView) view.findViewById(R.id.img_up);
    }


    /**
     * 控件操作
     */
    private void initOperation() {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) img_up.getLayoutParams();
        layoutParams.leftMargin = Utils.getScreenWidth(getActivity()) / 5;
        img_up.setLayoutParams(layoutParams);

        ed_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String phoneText;
                if (!hasFocus) {
                    phoneText = ed_phone.getText().toString();
                    if (phoneText.length() == 0) {
                        ed_phone.setError("手机号不能为空");
                    } else if (phoneText.length() < 11) {
                        ed_phone.setError("请输入正确手机号");
                    } else {

                    }
                }
            }
        });


        ed_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String passwordText;
                if (!hasFocus) {
                    passwordText = ed_phone.getText().toString();
                    if (passwordText.length() == 0) {
                        ed_phone.setError("密码不能为空");
                    } else if (passwordText.length() <= 6 && passwordText.length() >= 15) {
                        ed_phone.setError("请输入6-15位密码");
                    } else {

                    }
                }
            }
        });

        //登录按钮
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //测试
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        //忘记密码
        tv_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                startActivity(inteit);
            }
        });

        //微信登录
        img_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(Wechat.NAME);
            }
        });

        //微博登录
        img_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(SinaWeibo.NAME);
            }
        });

        //QQ登录
        img_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareOpration.otherLogin(QQ.NAME);
            }
        });

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
