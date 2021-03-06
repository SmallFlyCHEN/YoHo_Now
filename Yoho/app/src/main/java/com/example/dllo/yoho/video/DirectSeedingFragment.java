package com.example.dllo.yoho.video;

import android.view.View;
import android.widget.ListView;

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
 * Created by dllo on 16/11/25.
 */
public class DirectSeedingFragment extends BaseFragment{

    private ListView lv;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_directseeding;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.direct_seeding_lv);
    }

    //初始化数据
    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.DIRECTSEEDING_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DirectSeedingBean directSeedingBean = gson.fromJson(response, DirectSeedingBean.class);
                List<DirectSeedingBean.DataBean.ContentBean> list = directSeedingBean.getData().getContent();
                DirectSeedingAdapter seedingAdapter = new DirectSeedingAdapter(getActivity());
                seedingAdapter.setList(list);
                lv.setAdapter(seedingAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
