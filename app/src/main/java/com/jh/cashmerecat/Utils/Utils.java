package com.jh.cashmerecat.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;


/**
 * 工具类
 * Created by tmnt on 2016/5/26.
 */
public class Utils {

    private static final String TAG = "Utils";


    /**
     * 显示Toast
     *
     * @param context
     * @param title
     */
    public static void showToast(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
    }


    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    /**
     * 判断网络状态
     *
     * @param context
     * @return
     */
    public static int getNetWorkStatus(Context context) {
        int netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = Constants.NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = Constants.getNetWorkClass(context);
            }
        }
        return netWorkType;
    }

    /**
     * 打开网络设置界面
     */
    public static void openNetworkSetting(Context context) {

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开文件管理器
     *
     * @param context
     */
    public static void openContent(Context context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("file/*");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public static boolean isMobileConnected(Context context) {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return mMobileNetworkInfo.isAvailable();
    }

    /**
     * 适配4.4系统
     *
     * @param activity
     * @param b
     */
    public static void setTranslucentStatus(Activity activity, boolean b) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (b) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
