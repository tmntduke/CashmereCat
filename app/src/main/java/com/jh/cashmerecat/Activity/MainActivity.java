package com.jh.cashmerecat.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jh.cashmerecat.Fragment.NewsFragment;
import com.jh.cashmerecat.R;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity extends BaseActivity {

    private PagerBottomTabLayout mPagerBottomTabLayout;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatesBar(R.color.colorAccent);//设置状态栏颜色
        setContentView(R.layout.activity_main);

        initView();

        setDefaultFragment();
        showNavigationBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initView() {

        mPagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);

    }

    /**
     * 设置默认fragment
     */
    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.main_contain, NewsFragment.getInstance("22222")).commit();
    }

    @Override
    public void initOperation() {


    }

    @Override
    public void loadData() {

    }

    /**
     * 显示底部导航栏
     */
    private void showNavigationBar() {
        TabItemBuilder tabAttitude = new TabItemBuilder(this).create()
                .setDefaultColor(0xFFACACAC)
                .setSelectedColor(0xFF00796B)
                .setDefaultIcon(android.R.drawable.ic_menu_help)
                .setText("attitude")
                .setTag("这是一个Attitude")
                .build();

        TabItemBuilder tabInfomation = new TabItemBuilder(this).create()
                .setDefaultColor(0xFFACACAC)
                .setSelectedColor(0xFF00796B)
                .setDefaultIcon(android.R.drawable.ic_menu_help)
                .setText("infomation")
                .setTag("这是一个Infomation")
                .build();

        TabItemBuilder tabCircle = new TabItemBuilder(this).create()
                .setDefaultColor(0xFFACACAC)
                .setSelectedColor(0xFF00796B)
                .setDefaultIcon(android.R.drawable.ic_menu_help)
                .setText("circle")
                .setTag("这是一个Circle")
                .build();

        TabItemBuilder tabQuestion = new TabItemBuilder(this).create()
                .setDefaultColor(0xFFACACAC)
                .setSelectedColor(0xFF00796B)
                .setDefaultIcon(android.R.drawable.ic_menu_help)
                .setText("question")
                .setTag("这是一个Question")
                .build();

        Controller controller = mPagerBottomTabLayout.builder()
                .addTabItem(tabAttitude)
                .addTabItem(tabInfomation)
                .addTabItem(tabCircle)
                .addTabItem(tabQuestion)
                .build();


        controller.addTabItemClickListener(new OnTabItemSelectListener() {
            @Override
            public void onSelected(int index, Object tag) {
                switch (index) {
                    case 0:
                        manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.main_contain, NewsFragment.getInstance("22222"))
                                .commit();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onRepeatClick(int index, Object tag) {

            }
        });
    }
}
