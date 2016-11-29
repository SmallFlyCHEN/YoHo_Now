package com.example.dllo.yoho.recommend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.yoho.MyPoint;
import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/11/24.
 */

public class RecommendAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    List<RecommendCarouselBean.DataBean> list;
    Context context;
    ViewPager viewPager;
    ArrayList<MyPoint> points;

    public RecommendAdapter setPoints(ArrayList<MyPoint> points) {
        this.points = points;
        return this;
    }

    public RecommendAdapter setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        return this;
    }

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public RecommendAdapter setList(List<RecommendCarouselBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return list != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //轮播图的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.recommend_vp_iv);
        Picasso.with(context).load(list.get(position % list.size()).getImage()).into(iv);
        container.addView(view);
        viewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position) == object) {
            container.removeViewAt(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //实现点和图片的联动
    @Override
    public void onPageSelected(int position) {
        //判断点是否跟当前图片对应
        int a = position % list.size();
        for (MyPoint point : points) {
            point.setSelected(false);
        }
        if (a == position % points.size()) {
            points.get(a).setSelected(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
