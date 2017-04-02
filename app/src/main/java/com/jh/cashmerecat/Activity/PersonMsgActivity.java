package com.jh.cashmerecat.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jh.cashmerecat.Fragment.PickDialogFragment;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ImageUtils;
import com.jh.cashmerecat.Widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 详细个人信息界面
 * Created by tmnt on 2016/11/30.
 */
public class PersonMsgActivity extends BaseActivity {

    @Bind(R.id.btn_save)
    Button mBtnSave;//保存按钮
    @Bind(R.id.img_bg)
    ImageView mImgBg;//背景图
    @Bind(R.id.img_icon)
    CircleImageView mImgIcon;//用户头像
    @Bind(R.id.ed_nickname)
    EditText mEdNickname;//昵称
    @Bind(R.id.ed_phone)
    EditText mEdPhone;//电话号码
    @Bind(R.id.ed_email)
    EditText mEdEmail;//邮箱
    @Bind(R.id.ed_sign)
    EditText mEdSign;//个人签名
    @Bind(R.id.rg_sex)
    RadioGroup mRgSex;//性别
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.tv_role)
    TextView mTvRole;

    private boolean isNickname;
    private boolean isPhone;
    private boolean isMail;

    public static final int RESULT_IMAGE = 100;
    public static final int RESULT_CAMERA = 200;
    public static final int RESULT_CAMERA_BG = 300;
    public static final int RESULT_IMAGE_BG = 400;

    private static final String TAG = "PersonMsgActivity";

    private PickDialogFragment fDialogFragment;

    private static String TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath() + "image/jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatesBar(R.color.colorAccent);
        setContentView(R.layout.activity_personmsg);
        ButterKnife.bind(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fDialogFragment!=null){
            fDialogFragment.dismiss();
        }

    }

    @Override
    public void initOperation() {

        //1.用户昵称从服务器取出
        //2.判断用户身份

        mBtnSave.setOnClickListener(v -> {
            if (isNickname && isMail && isPhone) {
                //调web service
            } else if (!isPhone || !isMail || !isNickname || mEdSign.getText().toString().isEmpty()) {
                Snackbar.make(v, "请输入信息", Snackbar.LENGTH_SHORT).show();
            }
        });

        mImgBg.setOnClickListener(v ->
                showPickDialog(RESULT_CAMERA_BG, RESULT_IMAGE_BG)
        );

        mImgIcon.setOnClickListener(v ->
                showPickDialog(RESULT_CAMERA, RESULT_IMAGE)

        );


        mRgSex.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.man) {

            } else {

            }
        }));


        mEdPhone.setOnFocusChangeListener((v, focus) -> {
            if (mEdNickname.getText().toString().isEmpty()) {
                mEdNickname.setError("不能为空");
            } else {
                isNickname = true;
            }
        });

        mEdEmail.setOnFocusChangeListener((v, focus) -> {
            String regex = "\\d{11}";
            if (mEdPhone.getText().toString().isEmpty()) {
                mEdPhone.setError("不能为空");
            } else if (!mEdPhone.getText().toString().matches(regex)) {
                mEdPhone.setError("号码不正确");
            } else {
                isPhone = true;
            }
        });

        mEdSign.setOnFocusChangeListener((v, focus) -> {
            String regex = "[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+";
            if (mEdEmail.getText().toString().isEmpty()) {
                mEdEmail.setError("不能为空");
            } else if (!mEdEmail.getText().toString().matches(regex)) {
                mEdEmail.setError("格式不正确");
            } else {
                isMail = true;
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_IMAGE && data != null) {
                String s = ImageUtils.getImageFromGallery(PersonMsgActivity.this, data);

            } else if (requestCode == RESULT_IMAGE_BG && data != null) {
                String s = ImageUtils.getImageFromGallery(PersonMsgActivity.this, data);
            }
        }
    }

    /**
     * 显示选择弹框
     *
     * @param photo
     * @param image
     */
    private void showPickDialog(int photo, int image) {

        Log.i(TAG, "showPickDialog: start");

        fDialogFragment = new PickDialogFragment();
        fDialogFragment.show(getSupportFragmentManager(), "fDialogFragment");

        dialogOption(fDialogFragment, photo, image);
    }

    /**
     * 弹框操作
     *
     * @param fragment
     * @param photo
     * @param image
     */
    public void dialogOption(final PickDialogFragment fragment, int photo, int image) {
        fragment.setOnDoOptionOnDialog(new PickDialogFragment.OnDoOptionOnDialog() {
            @Override
            public void onTakePhoto(View view) {
                ImageUtils.toCamera(PersonMsgActivity.this, TEMP_IMAGE_PATH, photo);
            }

            @Override
            public void onChoosePhoto(View view) {
                ImageUtils.toGallery(image, PersonMsgActivity.this);
            }

            @Override
            public void onCancel(View view) {
                fragment.dismiss();
            }
        });
    }

}
