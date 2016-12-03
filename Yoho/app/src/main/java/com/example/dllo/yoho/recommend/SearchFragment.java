package com.example.dllo.yoho.recommend;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.example.dllo.yoho.volley.PostBody;

import java.util.HashMap;

/**
 * Created by dllo on 16/12/1.
 */

public class SearchFragment extends BaseFragment{

    private ListView lv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.search_lv);
    }

    @Override
    protected void initData() {
        //解析数据
        getData();
    }

    public static SearchFragment newInstance(int position) {

        //传值(id)
        Bundle args = new Bundle();
        //获取适配器里面获取的数据(从SearchActivity获取的)
        String message = SearchAdapter.getMessage(position);
        args.putString("key", message);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void getData() {

        //接收bundle传过来的值赋给map
        Bundle bundle = getArguments();
        String msg = bundle.get("key").toString();
        HashMap<String, String> map = new HashMap<>();
        final PostBody post = new PostBody();
        post.setChannelId(msg);
        map.put("parameters", post.m(post));
        NetHelper.MyRequest(URLValues.SEARCHCOMMNOL_URL, LvBean.class, new NetListener<LvBean>() {
            @Override
            public void successListener(LvBean response) {

                ListViewAdapter adapter = new ListViewAdapter(getActivity());
                adapter.setBean(response);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }
}
