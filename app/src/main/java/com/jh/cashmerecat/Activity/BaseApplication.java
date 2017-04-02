package com.jh.cashmerecat.Activity;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * Created by tmnt on 2016/11/28.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        SMSSDK.initSDK(this, "195f13e65d67a", "fd4428e05062452eaa6d2564d7ffb1e5");
    }


}
