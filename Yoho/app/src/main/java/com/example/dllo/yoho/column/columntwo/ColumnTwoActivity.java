package com.example.dllo.yoho.column.columntwo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseActivity;
import com.example.dllo.yoho.column.columthree.ColumnThreeActivity;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/9.
 */

public class ColumnTwoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private ColumnTwoAdapter adapter;
    private ImageView headIv;
    private ColumnTwoBean columnTwoBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_columutwo;
    }

    @Override
    protected void initView() {
        lv = (ListView) findViewById(R.id.columntwo_two_lv);
        View viewheader = LayoutInflater.from(this).inflate(R.layout.item_column_two_header, null);
        headIv = (ImageView) viewheader.findViewById(R.id.column_two_header);
        lv.addHeaderView(viewheader);
        adapter = new ColumnTwoAdapter(this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {

        //获取第一个界面传的数据
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = intent.getStringExtra("url");
        Picasso.with(this).load(url).into(headIv);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("page","1");
        hashMap.put("limit","20");
        hashMap.put("platform","4");
        hashMap.put("locale","zh-Hans");
        hashMap.put("language","zh-Hans");
        hashMap.put("udid","00000000000000063aa461b71c4cfcf");
        hashMap.put("curVersion","5.0.4");

        HashMap<String, String> map = new HashMap<>();
        map.put("udid","00000000000000063aa461b71c4cfcf");
        Gson gson = new Gson();
        String str = gson.toJson(map).toString();
        hashMap.put("authInfo", str);

        String values = gson.toJson(hashMap).toString();
        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("parameters", values);
        NetHelper.MyRequest(URLValues.COLUMN_TWO, ColumnTwoBean.class, new NetListener<ColumnTwoBean>() {
            @Override
            public void successListener(ColumnTwoBean response) {
                adapter.setColumnTwoBean(response);
                columnTwoBean = response;
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, valuesMap);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //应为头布局占位 所以要 -1
        String url = columnTwoBean.getData().getContent().get(position - 1).getPublishURL();
        Intent intent = new Intent(this, ColumnThreeActivity.class);
        if (position - 1 >= 0) {
            intent.putExtra("url", url);
        }
        startActivity(intent);
    }
}
