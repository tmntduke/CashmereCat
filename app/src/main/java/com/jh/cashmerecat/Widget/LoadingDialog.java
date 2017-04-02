package com.jh.cashmerecat.Widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jh.cashmerecat.R;
import com.jh.cashmerecat.Utils.Utils;

/**
 * 进度弹框
 * Created by tmnt on 2016/12/6.
 */
public class LoadingDialog extends Dialog {

    private static LoadingDialog mLoadingDialog;

    public LoadingDialog(Context context) {
        super(context);

    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static LoadingDialog createDialog(Context context) {
        mLoadingDialog = new LoadingDialog(context, R.style.ProgressDialog);
        mLoadingDialog.setContentView(R.layout.dialog_progress_lay);
        mLoadingDialog.setCanceledOnTouchOutside(true);

        Window window = mLoadingDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = Utils.getScreenWidth(context) / 3 * 2;
        window.setAttributes(layoutParams);

        return mLoadingDialog;
    }


}
