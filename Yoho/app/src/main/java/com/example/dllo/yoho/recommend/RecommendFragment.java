package com.example.dllo.yoho.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.MyPoint;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment implements View.OnClickListener {

    private ArrayList<MyPoint> points;
    private RecommendAdapter adapter;
    private Handler handler;
    private boolean flag = true;
    private ListView lv;
    private RecommendLvAdapter lvAdapter;
    private ViewPager viewPager;
    private LinearLayout pointLin;
    private List<RecommendCarouselBean.DataBean> carouseList;
    private ImageView drawer;
    private DrawerLayout drawerLayout;
    private ImageView search;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.recommend_lv);
        drawer = (ImageView) view.findViewById(R.id.iv_recommend_drawer);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.root_drawer);
        search = (ImageView) view.findViewById(R.id.recommend_iv_search);
        search.setOnClickListener(this);
    }

    //初始化数据
    @Override
    protected void initData() {
        //获取数据
        getData();
        //添加头布局
        addView();
        //抽屉
        openDrawer();
    }

    private void openDrawer() {
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    //添加头布局
    private void addView() {
        View headView = getActivity().getLayoutInflater().inflate(R.layout.carousel_picture, null);
        viewPager = (ViewPager) headView.findViewById(R.id.carousel_vp);
        pointLin = (LinearLayout) headView.findViewById(R.id.carousel_ll);
        lv.addHeaderView(headView);
        //轮播图
        Carousel();
    }

    //请求listView数据
    private void getData() {
        lvAdapter = new RecommendLvAdapter(getContext());
        NetHelper.MyRequest(URLValues.RECOMMEND_URL, RecommendLvBean.class, new NetListener<RecommendLvBean>() {
            @Override
            public void successListener(RecommendLvBean response) {
                List<RecommendLvBean.DataBean> list = response.getData();
                lvAdapter.setList(list);
                lv.setAdapter(lvAdapter);
        }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    //轮播图数据
    private void Carousel() {
        points = new ArrayList<>();
        adapter = new RecommendAdapter(getActivity());
        NetHelper.MyRequest(URLValues.CAROUSEL_URl, RecommendCarouselBean.class, new NetListener<RecommendCarouselBean>() {
            @Override
            public void successListener(RecommendCarouselBean response) {
                carouseList = response.getData();
                adapter.setList(carouseList);
                getPoint();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        viewPager.setAdapter(adapter);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //设置当前显示下一张图片
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return false;
            }
        });

        //开启一个线程让轮播图2秒跟换一次
        if (flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
            flag = false;
        }


        adapter.setViewPager(viewPager);
    }

    //圆点的设置
    public void getPoint() {
        for (int i = 0; i < carouseList.size(); i++) {
            MyPoint point = new MyPoint(context);
            if (i == 0) {
                point.setSelected(true);
            }

            //往集合中加点
            points.add(point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.leftMargin = 20;
            params.rightMargin = 20;
            //线性布局加点
            pointLin.addView(point, params);
        }
        adapter.setPoints(points);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //轮播图
//        handler.removeMessages(1);
    }

    //搜索键的点击事件
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }
}
