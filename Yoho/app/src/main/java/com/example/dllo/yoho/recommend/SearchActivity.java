package com.example.dllo.yoho.recommend;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseActivity;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;

/**
 * Created by dllo on 16/12/1.
 */

public class SearchActivity extends BaseActivity{

    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        tab = (TabLayout) findViewById(R.id.tab_search);
        vp = (ViewPager) findViewById(R.id.vp_search);
    }

    @Override
    protected void initData() {
        NetHelper.MyRequest(URLValues.SEARCHTAB_URL, SearchBean.class, new NetListener<SearchBean>() {
            @Override
            public void successListener(SearchBean response) {

                SearchAdapter adapter = new SearchAdapter(getSupportFragmentManager());
                adapter.setBean(response);
                vp.setAdapter(adapter);
                tab.setupWithViewPager(vp);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


}
