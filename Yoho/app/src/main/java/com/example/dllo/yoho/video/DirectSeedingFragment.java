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
        NetHelper.MyRequest(URLValues.DIRECTSEEDING_URL, DirectSeedingBean.class, new NetListener<DirectSeedingBean>() {
            @Override
            public void successListener(DirectSeedingBean response) {
                List<DirectSeedingBean.DataBean.ContentBean> list = response.getData().getContent();
                DirectSeedingAdapter seedingAdapter = new DirectSeedingAdapter(getActivity());
                seedingAdapter.setList(list);
                lv.setAdapter(seedingAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
