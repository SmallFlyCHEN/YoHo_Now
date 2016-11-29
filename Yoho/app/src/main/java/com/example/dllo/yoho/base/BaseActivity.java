package com.example.dllo.yoho.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    //设置布局的
    protected abstract int setLayout();
    //初始化组件
    protected abstract void initView();
    //初始化数据的方法
    protected abstract void initData();
}
