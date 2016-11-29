package com.example.dllo.yoho.column;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class ColumnFragment extends BaseFragment{

    private SwipeRefreshLayout sr;
    private RecyclerView rv;
    private ColumnAdapter columnAdapter;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_column;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        sr = (SwipeRefreshLayout) view.findViewById(R.id.column_sr);
        rv = (RecyclerView) view.findViewById(R.id.column_rv);
    }

    //初始化数据
    @Override
    protected void initData() {
        //解析数据
        getData();
    }

    //网络请求
    private void getData(){
        columnAdapter = new ColumnAdapter(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(URLValues.COLUMN_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                ColumnBean columnBean = gson.fromJson(response, ColumnBean.class);
                List<ColumnBean.DataBean> list = columnBean.getData();
                columnAdapter.setList(list);
                rv.setAdapter(columnAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rv.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
