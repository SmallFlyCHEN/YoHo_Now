package com.example.dllo.yoho;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dllo.yoho.Logon.LogonActivity;
import com.example.dllo.yoho.base.BaseActivity;
import com.example.dllo.yoho.column.ColumnFragment;
import com.example.dllo.yoho.community.CommunityFragment;
import com.example.dllo.yoho.magazine.MagazineFragment;
import com.example.dllo.yoho.mymagazine.MyMagazineActivity;
import com.example.dllo.yoho.recommend.RecommendFragment;
import com.example.dllo.yoho.video.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton recommend;
    private RadioButton column;
    private RadioButton community;
    private RadioButton magazine;
    private RadioButton video;
    private RecommendFragment recommendFragment;
    private DrawerLayout drawerLayout;
    private ImageView logon;
    private TextView myMagazine;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    //初始化组件
    @Override
    protected void initView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.root_drawer);
        recommend = (RadioButton) findViewById(R.id.main_recommend);
        column = (RadioButton) findViewById(R.id.main_column);
        community = (RadioButton) findViewById(R.id.main_community);
        magazine = (RadioButton) findViewById(R.id.main_magazine);
        video = (RadioButton) findViewById(R.id.main_video);
        logon = (ImageView) findViewById(R.id.main_logon);
        myMagazine = (TextView) findViewById(R.id.drawer_magazine);

        recommend.setOnClickListener(this);
        column.setOnClickListener(this);
        community.setOnClickListener(this);
        magazine.setOnClickListener(this);
        video.setOnClickListener(this);
        logon.setOnClickListener(this);
        myMagazine.setOnClickListener(this);

    }

    //初始化数据
    @Override
    protected void initData() {
        recommendFragment = new RecommendFragment();
        addFragment(recommendFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_recommend:
                addFragment(recommendFragment);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.main_column:
                ColumnFragment columnFragment = new ColumnFragment();
                addFragment(columnFragment);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.main_community:
                CommunityFragment communityFragment = new CommunityFragment();
                addFragment(communityFragment);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.main_magazine:
                MagazineFragment magazineFragment = new MagazineFragment();
                addFragment(magazineFragment);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.main_video:
                VideoFragment videoFragment = new VideoFragment();
                addFragment(videoFragment);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
            case R.id.main_logon:
                Intent intent = new Intent(this, LogonActivity.class);
                startActivity(intent);
                break;
            case R.id.drawer_magazine:
                Intent intentMagazine = new Intent(this, MyMagazineActivity.class);
                startActivity(intentMagazine);
                break;
        }
    }

    private void addFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_fragment, fragment);
        ft.commit();
    }
}
