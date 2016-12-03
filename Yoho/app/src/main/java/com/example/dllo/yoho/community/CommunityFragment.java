package com.example.dllo.yoho.community;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class CommunityFragment extends BaseFragment{

    private ListView lv;
    private Banner banner;
    private ArrayList<Integer> arrayList;
    private FancyCoverFlow fan;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_community;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.community_lv);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_community_header, null);
        banner = (Banner) headerView.findViewById(R.id.community_header_banner);
        fan = (FancyCoverFlow) headerView.findViewById(R.id.community_header_fan);
        lv.addHeaderView(headerView);
    }

    //初始化数据
    @Override
    protected void initData() {
        //获取数据
        getData();
        //添加头布局
        addView();
        //FancyCover的解析
        getFancyCover();
    }

    private void getFancyCover() {
        NetHelper.MyRequest(URLValues.COMMUNITY_FCF_URL, CommunityFancyCoverFlowBean.class, new NetListener<CommunityFancyCoverFlowBean>() {
            @Override
            public void successListener(CommunityFancyCoverFlowBean response) {
                CommunityFancyCoverFlowAdapter flowAdapter = new CommunityFancyCoverFlowAdapter(getActivity());
                flowAdapter.setFancyCoverFlowBean(response);
                fan.setAdapter(flowAdapter);
                flowAdapter.notifyDataSetChanged();
                //透明度
                fan.setUnselectedAlpha(1);
                //饱和度
                fan.setUnselectedSaturation(0.5f);
                //设置规模
                fan.setUnselectedScale(0.3f);
                //设置间距
                fan.setSpacing(-50);
                //最大旋转度
                fan.setMaxRotation(0);
                //集缩减重力
                fan.setScaleDownGravity(0.5f);
                //设置动作间距
                fan.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void addView() {

        arrayList = new ArrayList<>();
        //死数据(单独轮播)添加图片
        arrayList.add(R.mipmap.yohocomtop);
        arrayList.add(R.mipmap.yohocomtop);

        Log.d("CommunityFragment", "arrayList:" + arrayList);
        //设置风格
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片的加载程序
        banner.setImageLoader(new MyImage());
        //设置图片
        banner.setImages(arrayList);
        //自动播放
        banner.isAutoPlay(true);
        //设置延迟时间
        banner.setDelayTime(2000);
        banner.start();
    }

    private void getData() {

        NetHelper.MyRequest(URLValues.COMMUNITY_URL, CommunityBean.class, new NetListener<CommunityBean>() {
            @Override
            public void successListener(CommunityBean response) {
                List<CommunityBean.DataBean.ListBean> list = response.getData().getList();
                CommunityAdapter adapter = new CommunityAdapter(getActivity());
                adapter.setList(list);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }


    //回调解析图片
    private class MyImage extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
