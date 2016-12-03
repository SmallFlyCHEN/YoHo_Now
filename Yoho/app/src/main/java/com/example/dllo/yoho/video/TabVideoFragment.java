package com.example.dllo.yoho.video;


import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
public class TabVideoFragment extends BaseFragment{

    private ListView lv;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_tabvideo;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.tab_video_lv);
    }

    //初始化数据
    @Override
    protected void initData() {
        NetHelper.MyRequest(URLValues.TABVIDEO_URL, TabVideoBean.class, new NetListener<TabVideoBean>() {
            @Override
            public void successListener(TabVideoBean response) {
                List<TabVideoBean.DataBean.ContentBean> list = response.getData().getContent();
                TabVideoAdapter tabVideoAdapter = new TabVideoAdapter(getActivity());
                tabVideoAdapter.setList(list);
                lv.setAdapter(tabVideoAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
