package com.example.dllo.yoho.magazine;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;

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
        NetHelper.MyRequest(URLValues.WALLPAPER_URL, WallpaperBean.class, new NetListener<WallpaperBean>() {
            @Override
            public void successListener(WallpaperBean response) {
                List<WallpaperBean.DataBean.WallpaperListBean> list = response.getData().getWallpaperList();
                WallPaperAdapter paperAdapter = new WallPaperAdapter(getActivity());
                paperAdapter.setList(list);
                lv.setAdapter(paperAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
