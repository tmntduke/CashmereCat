package com.jh.cashmerecat.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ShareOpration;

/**
 * Created by E540 on 2016/12/1.
 */
public class ForgetPasswordActivity extends BaseActivity {
    private EditText mEdPhone; //号码编辑框
    private EditText mEdPassword; //密码编辑框
    private EditText mEdAgainPassword;//再次输入密码编辑框
    private EditText mEdValidate;//验证码编辑框
    private TextView mBtnValidate;//获取验证码按钮
    private Button mBtnChange;//完成按钮
    private ImageView mImgShow;
    private ImageView mImgAgainShow;

    private boolean isPhone;//号码判断标志位
    private boolean isValidate;//验证码判断标志位
    private boolean isPassword;//密码判断标志位
    private boolean isAgainPassword;//再次输入密码
    private boolean isHiden;//隐藏查看密码按钮

    private static int count = 0;//倒计时
    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        initView();
        checkUser();
        initOpration();
    }

    /**
     * 初始化控件
     *
     */
    private void initView() {
        mEdPhone = (EditText) findViewById(R.id.ed_phone);
        mEdPassword = (EditText) findViewById(R.id.ed_password);
        mEdAgainPassword =(EditText)findViewById(R.id.ed_againpassword);
        mEdValidate = (EditText)findViewById(R.id.ed_validate);
        mBtnValidate = (TextView)findViewById(R.id.btn_validate);
        mBtnChange = (Button)findViewById(R.id.btn_change);
        mImgShow = (ImageView)findViewById(R.id.img_show);
        mImgAgainShow = (ImageView)findViewById(R.id.img_againshow);
    }


    @Override
    public void initOpration() {
        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否存在账号
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
            }
        });


        mImgShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

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
            }
        });
        mImgAgainShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case  MotionEvent.ACTION_DOWN:
                        mEdAgainPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP:
                        mEdAgainPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }
                mEdAgainPassword.setSelection(mEdAgainPassword.getText().length());
                return true;
            }
        });

    }

       /**
     * 验证用户信息
     */
    private void checkUser() {

        mEdValidate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String regex = "\\d{11}";
                if (mEdPhone.getText().toString().isEmpty()) {
                    mEdPhone.setError("号码不能为空");
                } else if (!mEdPhone.getText().toString().matches(regex)) {
                    mEdPhone.setError("号码不正确");
                } else {
                    isPhone = true;
                }
            }
        });

        mEdPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String regex = "\\d{4}";
                if (mEdValidate.getText().toString().isEmpty()) {
                    mEdValidate.setError("验证码不能为空");
                } else if (!mEdValidate.getText().toString().matches(regex)) {
                    mEdValidate.setError("验证码不正确");
                } else {
                    isValidate = true;
                }
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

        mEdAgainPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String againText = mEdAgainPassword.getText().toString();
                if(againText.isEmpty()){
                    mEdPassword.setError("请先输入新密码");
                }else if(!againText.equals(mEdPassword.getText().toString())){
                    mEdAgainPassword.setError("与上面新密码不符");
                }else{
                    isAgainPassword =true ;
                }
            }
        });

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    //修改密码

                } else if (!isPhone && !isValidate && !isPassword && !isAgainPassword) {
                    Snackbar.make(v, "请输入信息", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
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
