package com.jh.cashmerecat.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jh.cashmerecat.Adapter.ArticleCategoryAdapter;
import com.jh.cashmerecat.Detail.UserData;
import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.ImageUtils;
import com.jh.cashmerecat.Widget.LoadingDialog;
import com.jh.cashmerecat.Widget.PickCategory;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;

/**
 * 文章投稿界面
 * Created by tmnt on 2016/12/5.
 */
public class PublicArticleActivity extends BaseActivity {

    @Bind(R.id.btn_save)
    TextView mBtnSave;//保存
    @Bind(R.id.btn_publish)
    TextView mBtnPublish;//发布
    @Bind(R.id.img_picture)
    ImageView mImgPicture;//添加图片按钮
    @Bind(R.id.ed_title)
    EditText mEdTitle;//添加标题
    @Bind(R.id.editor)
    RichEditor mEditor;//富文本编辑器
    @Bind(R.id.rb_original)
    RadioButton mRbOriginal;//文章类型选择
    @Bind(R.id.rb_reship)
    RadioButton mRbReship;
    @Bind(R.id.rg_type)
    RadioGroup mRgType;
    @Bind(R.id.pick_category)
    PickCategory mPickCategory;//选择分类

    public static final int REQUES_CODE = 101;
    private String name;
    private Bitmap photo;

    private static final String TAG = "PublicArticleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatesBar(R.color.colorAccent);
        setContentView(R.layout.activity_article_contribute);
        ButterKnife.bind(this);
    }

    @Override
    public void initOpration() {

        SpannableString spannableString = new SpannableString("请输入标题(30字以内)");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.5f);
        spannableString.setSpan(sizeSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mEdTitle.setHint(spannableString);

        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("编辑正文...");


        mRbOriginal.setChecked(true);
        mRbOriginal.setTextColor(Color.parseColor("#ffffff"));

        mRgType.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == R.id.rb_original) {
                mRbOriginal.setTextColor(Color.parseColor("#ffffff"));
                mRbReship.setTextColor(Color.parseColor("#000000"));
            } else {
                mRbReship.setTextColor(Color.parseColor("#ffffff"));
                mRbOriginal.setTextColor(Color.parseColor("#000000"));
            }
        }));

        mPickCategory.setOnClickTextListener(view -> {
            showDialog();
        });

        mImgPicture.setOnClickListener(v -> {
            ImageUtils.toGallery(REQUES_CODE, PublicArticleActivity.this);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUES_CODE) {
                getImageToView(data);

                //富文本显示上传的图片
            }
        }
    }

    /**
     * 获取图片
     *
     * @param
     */
    private void getImageToView(Intent data) {
        if (data != null) {
            photo = BitmapFactory.decodeFile(ImageUtils
                    .getImageFromGallery(PublicArticleActivity.this, data));
            name = UUID.randomUUID() + ".jpg";
            // 图片保存到临时文件
            //FileUtils.saveBitmap(photo, name);

            //content = BitmapToBase64Util.bitmapToBase64(photo);
            //上传图片到服务器
            //显示上传进度
//            LoadingDialog dialog=new LoadingDialog(PublicArticleActivity.this);
//            dialog.show();
            LoadingDialog dialog = LoadingDialog.createDialog(PublicArticleActivity.this);
            dialog.show();
        }
    }

    /**
     * 显示列表弹框
     */
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublicArticleActivity.this);
        View view = LayoutInflater.from(PublicArticleActivity.this)
                .inflate(R.layout.dialog_category_lay, null, false);
        ListView listView = (ListView) view.findViewById(R.id.lv_category);
        ArticleCategoryAdapter adapter = new ArticleCategoryAdapter(UserData.getCategory(false), PublicArticleActivity.this);
        listView.setAdapter(adapter);


        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialogOpration(listView, dialog);
        dialog.show();
    }


    /**
     * 加载listView
     *
     * @param view
     */
    private void dialogOpration(ListView view, Dialog dialog) {

        view.setOnItemClickListener((parent, view1, position, id) -> {
            mPickCategory.setCategoryText(UserData.getCategory(false).get(position).getCategory_name());
            mPickCategory.setTag(UserData.getCategory(false).get(position).getCategoryID());
            Log.i(TAG, "dialogOpration: " + mPickCategory.getTag());
            dialog.dismiss();
        });
    }

}
