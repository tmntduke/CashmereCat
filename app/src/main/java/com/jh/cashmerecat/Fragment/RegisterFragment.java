package com.jh.cashmerecat.Fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.cashmerecat.Activity.PersonMsgActivity;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ShareOpration;
import com.jh.cashmerecat.Utils.Utils;

/**
 * 用户注册界面
 * Created by tmnt on 2016/11/28.
 */
public class RegisterFragment extends Fragment {

    private EditText mEdPhone; //号码编辑框
    private EditText mEdPassword; //密码编辑框
    private EditText mEdValidate;//验证码编辑框
    private TextView mBtnValidate;//获取验证码按钮
    private Button mBtnRegister;//注册按钮
    private ImageView mImgShow;
    private ImageView img_up;

    private boolean isPhone;//号码判断标志位
    private boolean isValidate;//验证码判断标志位
    private boolean isPassword;//密码判断标志位
    private boolean isHiden;//隐藏查看密码按钮

    private static int count = 0;//倒计时
    private CountDownTimer timer;

    private static final String TAG = "RegisterFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.fragment_register, container, false);

        initView(view);
        checkUser();
        initOpration();

        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mEdPhone = (EditText) view.findViewById(R.id.ed_phone);
        mEdPassword = (EditText) view.findViewById(R.id.ed_password);
        mEdValidate = (EditText) view.findViewById(R.id.ed_validate);
        mBtnValidate = (TextView) view.findViewById(R.id.btn_validate);
        mBtnRegister = (Button) view.findViewById(R.id.btn_register);
        mImgShow = (ImageView) view.findViewById(R.id.img_show);
        img_up = (ImageView) view.findViewById(R.id.img_up);
    }

    /**
     * 对控件操作
     */
    private void initOpration() {

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) img_up.getLayoutParams();
        layoutParams.leftMargin = Utils.getScreenWidth(getActivity()) / 7 * 5;
        img_up.setLayoutParams(layoutParams);

        mBtnValidate.setOnClickListener(v -> {
            //判断是否注册
            //1.获取验证码
            if (isPhone) {
                ShareOpration.SMSValidate("86", mEdPhone.getText().toString());
            }

            //2.进行倒计时
            timer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Message msg = Message.obtain();
                    msg.what = 0001;
                    msg.obj = millisUntilFinished;
                    new CountDownHandler().sendMessage(msg);
                }

                @Override
                public void onFinish() {
                    Message msg = Message.obtain();
                    msg.what = 0002;
                    new CountDownHandler().sendMessage(msg);
                }
            };
            timer.start();
            //Toast.makeText(getActivity(),"start",Toast.LENGTH_SHORT).show();

        });


        mImgShow.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mEdPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    break;
                case MotionEvent.ACTION_UP:
                    mEdPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;
            }
            mEdPassword.setSelection(mEdPassword.getText().length());
            return true;

        });


    }

    /**
     * 验证用户信息
     */
    private void checkUser() {

        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11) {
                    isPhone = true;
                }
            }
        });

        mEdValidate.setOnFocusChangeListener((v, focus) -> {
            String regex = "\\d{11}";
            if (mEdPhone.getText().toString().isEmpty()) {
                mEdPhone.setError("号码不能为空");
            } else if (!mEdPhone.getText().toString().matches(regex)) {
                mEdPhone.setError("号码不正确");
            } else {
                isPhone = true;
            }

        });

        mEdPassword.setOnFocusChangeListener((v, focus) -> {
            String regex = "\\d{4}";
            if (mEdValidate.getText().toString().isEmpty()) {
                mEdValidate.setError("验证码不能为空");
            } else if (!mEdValidate.getText().toString().matches(regex)) {
                mEdValidate.setError("验证码不正确");
            } else {
                isValidate = true;
            }

        });

        mEdPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //显示查看密码按钮
                if (isHiden && !mEdPassword.getText().toString().isEmpty()) {
                    mImgShow.setVisibility(View.VISIBLE);
                    isHiden = false;
                }
            }
        });

        mBtnRegister.setOnClickListener(v -> {
            String regex = "(?=.*\\d)(?=.*[a-zA-Z]).{6,15}";//密码验证
            if (isPhone && isValidate && mEdPassword.getText().toString().isEmpty()) {
                mImgShow.setVisibility(View.GONE);
                mEdPassword.setError("密码不能为空");
                isHiden = true;
            } else if (isPhone && isValidate && !mEdPassword.getText().toString().matches(regex)) {
                mImgShow.setVisibility(View.GONE);
                mEdPassword.setError("密码不正确");
                isHiden = true;
            } else if (isPhone && isValidate) {
                //调webservice；
                //注册

                //测试
                Intent intent = new Intent(getActivity(), PersonMsgActivity.class);
                startActivity(intent);

            } else if (!isPhone && !isValidate && !isPassword) {
                Snackbar.make(v, "请输入信息", Snackbar.LENGTH_SHORT).show();
                Log.i(TAG, "onFocusChange: start");
            }

        });
    }

    /**
     * 获取fragment实例
     *
     * @return
     */
    public static Fragment getInstance() {

        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    /**
     * 显示按钮上的倒计时
     */
    class CountDownHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0001) {
                //验证码倒计时
                mBtnValidate.setEnabled(false);
                mBtnValidate.setText(String.valueOf((long) msg.obj / 1000));
            } else if (msg.what == 0002) {
                //验证码超时
                timer.cancel();
                mBtnValidate.setText("获取验证码");
                mBtnValidate.setEnabled(true);
            }
        }
    }
}
