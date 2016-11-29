package com.example.dllo.yoho.magazine;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
public class WallpaperFragment extends BaseFragment{

    private TextView journal;
    private TextView month;
    private ImageView zeroIv, oneIv, twoIv, threeIv;
    private ListView lv;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_wallpaper;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.wallpaper_lv);
    }
    //初始化数据
    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.WALLPAPER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                WallpaperBean paperBean = gson.fromJson(response, WallpaperBean.class);
                List<WallpaperBean.DataBean.WallpaperListBean> list = paperBean.getData().getWallpaperList();
                WallPaperAdapter paperAdapter = new WallPaperAdapter(getActivity());
                paperAdapter.setList(list);
                lv.setAdapter(paperAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
