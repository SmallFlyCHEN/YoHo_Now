package com.example.dllo.yoho.column;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.column.columntwo.ColumnTwoActivity;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class ColumnFragment extends BaseFragment implements OnItemClickListener {

    private LRecyclerView rv;
    private ColumnAdapter columnAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private String columnNum = "0";
    private HashMap<String, String> map;
    private HashMap<String, String> has;
    private int initial = 1;
    private String first = "";
    private Gson gson;
    private ColumnBean columnBean;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_column;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        rv = (LRecyclerView) view.findViewById(R.id.column_rv);
        columnAdapter = new ColumnAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(columnAdapter);
        rv.setAdapter(lRecyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        lRecyclerViewAdapter.setOnItemClickListener(this);
    }

    //初始化数据
    @Override
    protected void initData() {
        //解析数据
        getData();
        //上拉加载下拉刷新
        onListener();
        //post
        columnHashMap();
    }

    //网络请求
    private void getData() {
        columnHashMap();
        NetHelper.MyRequest(URLValues.COLUMN_URl, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                List<ColumnBean.DataBean> list = response.getData();

                columnBean = response;

                columnAdapter.setList(list);
                //判断是否第一次进入
                if (initial == 1) {
                    first = response.getData().get(response.getData().size() - 1).getCreate_time();
                }
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_column_header, null);
                lRecyclerViewAdapter.addHeaderView(view);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, has);
    }
    private void onListener() {
        //下拉刷新
        rv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetHelper.MyRequest(URLValues.COLUMN_URl, ColumnBean.class, new NetListener<ColumnBean>() {
                    @Override
                    public void successListener(ColumnBean response) {
                        List<ColumnBean.DataBean> list = response.getData();
                        columnAdapter.setList(list);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        rv.setLayoutManager(manager);
                    }
                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, has);
            }
        });

        //上拉加载
        rv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                //第一次的数据只加载一次
                if (initial == 1){
                    columnNum = first;
                    map.put("lastTime", columnNum);
                    String values = gson.toJson(map).toString();
                    has.put("parameters", values);
                    initial = -1;
                }
                NetHelper.MyRequest(URLValues.COLUMN_URl, ColumnBean.class, new NetListener<ColumnBean>() {
                    @Override
                    public void successListener(ColumnBean response) {
                        List<ColumnBean.DataBean> list = response.getData();
                        columnAdapter.setList(list);
                        if (response.getData().isEmpty()){
                            columnNum = "0";
                        }else {
                            columnNum = response.getData().get(response.getData().size() - 1).getCreate_time();
                        }
                        //更新Map的值
                        map.put("lastTime", columnNum);
                        String str = gson.toJson(map).toString();
                        has.put("parameters", str);
                    }
                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, map);
            }
        });
    }

    //post HashMap
    private void columnHashMap(){
        map = new HashMap<>();
        map.put("limit", "12");

        map.put("lastTime", columnNum);
        map.put("startTime", "0");
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("udid", "00000000000000063aa461b71c4cfcf");
        gson = new Gson();
        String str = gson.toJson(hashMap).toString();
        map.put("authInfo", str);
        String values = gson.toJson(map).toString();
        has = new HashMap<>();
        has.put("parameters", values);
    }


    @Override
    public void onItemClick(View view, int i) {
        String id = columnBean.getData().get(i).getId();
        String url = columnBean.getData().get(i).getCover();
        Intent intent = new Intent(getActivity(), ColumnTwoActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
