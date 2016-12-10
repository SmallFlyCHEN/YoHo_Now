package com.example.dllo.yoho.video;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.video.videotwo.VideoTwoActivity;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
public class TabVideoFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView lv;
    private TabVideoBean tabVideoBean;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_tabvideo;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.tab_video_lv);
        lv.setOnItemClickListener(this);
    }

    //初始化数据
    @Override
    protected void initData() {
        NetHelper.MyRequest(URLValues.TABVIDEO_URL, TabVideoBean.class, new NetListener<TabVideoBean>() {

            @Override
            public void successListener(TabVideoBean response) {
                List<TabVideoBean.DataBean.ContentBean> list = response.getData().getContent();
                TabVideoAdapter tabVideoAdapter = new TabVideoAdapter(getActivity());
                tabVideoBean = response;
                tabVideoAdapter.setList(list);
                lv.setAdapter(tabVideoAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tabID = tabVideoBean.getData().getContent().get(position).getId();
        String tabCid = tabVideoBean.getData().getContent().get(position).getCid();

        Intent intent = new Intent(getActivity(), VideoTwoActivity.class);
        intent.putExtra("id", tabID);
        intent.putExtra("cid", tabCid);
        startActivity(intent);
    }
}
