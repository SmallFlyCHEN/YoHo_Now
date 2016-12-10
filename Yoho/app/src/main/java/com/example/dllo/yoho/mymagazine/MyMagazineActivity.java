package com.example.dllo.yoho.mymagazine;

import android.widget.GridView;

import com.example.dllo.yoho.GreenDao.CollectBean;
import com.example.dllo.yoho.GreenDao.DBTools;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */

public class MyMagazineActivity extends BaseActivity{

    private GridView gv;
    private DBTools tools;
    private GridViewAdapter adapter;
    private ArrayList<CollectBean> arrayList;


    @Override
    protected int setLayout() {
        return R.layout.activity_mymagazine;
    }

    @Override
    protected void initView() {
        gv = (GridView) findViewById(R.id.mymagazine_gv);
        tools = new DBTools();
        adapter = new GridViewAdapter(this);
        arrayList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        querydata();
    }

    private void querydata() {
        List<CollectBean> data=DBTools.getCollectBeanDao().queryAll();

        for (int i = 0; i <data.size(); i++) {
            arrayList.add(data.get(i));
        }
        adapter.setCollectBeanList(arrayList);
        gv.setAdapter(adapter);
    }
}
