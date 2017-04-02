package com.jh.cashmerecat.Utils;

import com.jh.cashmerecat.Fragment.LoginFragment;
import com.jh.cashmerecat.Model.GetUserInfo;
import com.jh.cashmerecat.Model.ShareInfo;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * shareSDK封装类
 * Created by tmnt on 2016/11/28.
 */
public class ShareOpration {

    /**
     * 第三方登录
     */
    public static GetUserInfo otherLogin(String loginType) {

        Platform platform = ShareSDK.getPlatform(loginType);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行

        if (platform.isAuthValid()) {

            String userId = platform.getDb().getUserId();
            if (userId != null) {
                new LoginFragment.MyHanlder().sendEmptyMessage(101);
                return null;
            }
        }
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }


            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
        //weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息

        return null;
    }

    /**
     * 分享
     *
     * @param info
     */
    public static void share(ShareInfo info) {
    }

    /**
     * 短信验证码
     *
     * @param country
     * @param phone
     */
    public static void SMSValidate(String country, String phone) {

        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功

                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调

        SMSSDK.getVerificationCode(country, phone);

    }
}
