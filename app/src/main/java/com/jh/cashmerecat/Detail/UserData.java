package com.jh.cashmerecat.Detail;

import com.jh.cashmerecat.Model.CategoryArticlInfo;
import com.jh.cashmerecat.Model.CategoryInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地数据据调用
 * Created by tmnt on 2016/12/1.
 */
public class UserData {

    public static List<CategoryInfo> getCategory(boolean slide) {

        List<CategoryInfo> categoryInfos = new ArrayList<>();

        for (int i = 0; i <= 4; i++) {
            CategoryInfo info = new CategoryInfo();
            info.setCategoryID("c00000" + String.valueOf(i));
            info.setCategory_name("type" + String.valueOf(i));
            if (slide) {
                info.setCategory_image(imageUrl().get(i));
            }
            categoryInfos.add(info);
        }

        return categoryInfos;

    }

    public static List<CategoryArticlInfo> getArticlList() {

        List<CategoryArticlInfo> categoryArticlInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CategoryArticlInfo info = new CategoryArticlInfo();
            info.setArticle_category_id("c00000" + String.valueOf(i));
            info.setBg_image(imageUrl().get(i));
            info.setContent("aaaaaaaaaaaaaaaaaaaaaa" + String.valueOf(i));
            info.setCover_image(imageUrl().get(10 - i));
            info.setIcon(imageUrl().get(i));
            info.setId("a000000" + String.valueOf(i));
            info.setLabel("article" + String.valueOf(i));
            info.setNickname("tmnt" + String.valueOf(i));
            info.setSummary("ccccc" + String.valueOf(i));
            info.setTitle("bbbbbbb" + String.valueOf(i));

            categoryArticlInfos.add(info);
        }

        return categoryArticlInfos;

    }

    public static List<String> imageUrl() {

        List<String> list = new ArrayList<>();
        list.add("http://tva1.sinaimg.cn/crop.0.0.1080.1080.1024/871462bbjw8ew5s8g3y7oj20u00u0wg5.jpg");
        list.add("http://www.qq1234.org/uploads/allimg/150121/3_150121144650_12.jpg");
        list.add("http://www.sheyou114.com/wp-content/uploads/2014/06/b2c4514a3a942b6021878fc4efb22138.jpg");
        list.add("http://img1.gtimg.com/0/3/385/38567_1200x1000_0.jpg");
        list.add("http://img4.cache.netease.com/photo/0026/2015-05-19/APVC513454A40026.jpg");
        list.add("http://i2.hdslb.com/video/93/932f76fd9e7856acb8b07d28d438ee3c.jpg");
        list.add("http://pic48.nipic.com/file/20140917/19611695_145303148000_2.jpg");
        list.add("http://pic89.huitu.com/res/20161031/1160721_20161031175201057020_1.jpg");
        list.add("http://h.hiphotos.baidu.com/image/h%3D200/sign=2a137bccdc39b60052ce08b7d9513526/b58f8c5494eef01f05f460eae9fe9925bc317d38.jpg");
        list.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/06/ChMkJ1bKyhmIQFUTABNsnM0g-twAALIWgPk0D0AE2y0479.jpg");
        list.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/03/ChMkJlbKxtqIF93BABJ066MJkLcAALHrQL_qNkAEnUD253.jpg");

        return list;
    }

}
