package com.example.dllo.yoho.recommend;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yoho.MyPoint;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment {

    private ArrayList<MyPoint> points;
    private RecommendAdapter adapter;
    private Handler handler;
    private boolean flag = true;
    private ListView lv;
    private RecommendLvAdapter lvAdapter;
    private ViewPager viewPager;
    private LinearLayout pointLin;
    private List<RecommendCarouselBean.DataBean> carouseList;
    private ImageView imageView;
    private DrawerLayout drawerLayout;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.recommend_lv);
        imageView = (ImageView) view.findViewById(R.id.iv_recommend_drawer);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.root_drawer);
    }

    //初始化数据
    @Override
    protected void initData() {
        getData();
        addView();
        openDrawer();
    }

    private void openDrawer() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    //添加头布局
    private void addView() {
        View headview = getActivity().getLayoutInflater().inflate(R.layout.carousel_picture, null);
        viewPager = (ViewPager) headview.findViewById(R.id.carousel_vp);
        pointLin = (LinearLayout) headview.findViewById(R.id.carousel_ll);
        lv.addHeaderView(headview);
        //轮播图
        Carousel();
    }

    //请求listView数据
    private void getData() {
        lvAdapter = new RecommendLvAdapter(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(URLValues.RECOMMEND_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RecommendLvBean lvBean = gson.fromJson(response, RecommendLvBean.class);
                List<RecommendLvBean.DataBean> list = lvBean.getData();
                lvAdapter.setList(list);
                lv.setAdapter(lvAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    //轮播图数据
    private void Carousel() {
        points = new ArrayList<>();
        adapter = new RecommendAdapter(getActivity());
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.CAROUSEL_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RecommendCarouselBean carouselBean = gson.fromJson(response, RecommendCarouselBean.class);
                carouseList = carouselBean.getData();
                adapter.setList(carouseList);
                getPoint();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

        viewPager.setAdapter(adapter);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //设置当前显示下一张图片
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return false;
            }
        });

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
            MyPoint point = new MyPoint(getActivity());
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
}
