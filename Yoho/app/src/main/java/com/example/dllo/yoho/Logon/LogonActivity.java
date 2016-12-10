package com.example.dllo.yoho.Logon;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.yoho.R;
import com.example.dllo.yoho.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/5.
 * 登录界面
 */

public class LogonActivity extends BaseActivity{

    public static ViewPager vp;


    @Override
    protected int setLayout() {
        return R.layout.activity_logon;
    }

    //初始化组件
    @Override
    protected void initView() {
        vp = (ViewPager) findViewById(R.id.logon_vp);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new LogonFragment());
        arrayList.add(new RegisterFragment());

        LogonAdapter adapter = new LogonAdapter(getSupportFragmentManager());
        adapter.setArrayList(arrayList);
        vp.setAdapter(adapter);
    }

}
