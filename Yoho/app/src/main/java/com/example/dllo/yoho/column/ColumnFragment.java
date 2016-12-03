package com.example.dllo.yoho.column;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class ColumnFragment extends BaseFragment{

    private LRecyclerView rv;
    private ColumnAdapter columnAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private RecyclerViewHeader header;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_column;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        rv = (LRecyclerView) view.findViewById(R.id.column_rv);
        header = (RecyclerViewHeader) view.findViewById(R.id.column_header);
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
        NetHelper.MyRequest(URLValues.COLUMN_URl, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                List<ColumnBean.DataBean> list = response.getData();
                columnAdapter.setList(list);
                lRecyclerViewAdapter = new LRecyclerViewAdapter(columnAdapter);
                rv.setAdapter(lRecyclerViewAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                rv.setLayoutManager(manager);
                header.attachTo(rv, true);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
