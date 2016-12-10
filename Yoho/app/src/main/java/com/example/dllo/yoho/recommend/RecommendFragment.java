package com.example.dllo.yoho.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.dllo.yoho.MyPoint;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */

public class RecommendFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

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
    private SwipeToLoadLayout swipeToLoadLayout;
    private int bodyNum = 0;
    private HashMap<String, String> mapData;
    private HashMap<String, String> map;
    private Gson gson;
    private List<RecommendLvBean.DataBean> list;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        lv = (ListView) view.findViewById(R.id.swipe_target);
        drawer = (ImageView) view.findViewById(R.id.iv_recommend_drawer);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.root_drawer);
        search = (ImageView) view.findViewById(R.id.recommend_iv_search);
        search.setOnClickListener(this);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
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
        //自动更新
        autoRefresh();
        //post解析
        RecommendMap();

    }

    private void RecommendMap() {
        map = new HashMap<>();
        map.put("newsEndtime", "0");
        map.put("otherEndTime", "0");
        map.put("magazineType", "3");
        map.put("WallpaperType", "3");
        map.put("scale", "2");
        map.put("num", String.valueOf(bodyNum));
        map.put("platform", "4");
        map.put("locale", "zh-Hans");
        map.put("language", "zh-Hans");
        map.put("udid", "00000000000000063aa461b71c4cfcf");
        map.put("curVersion", "5.0.4");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("udid", "00000000000000063aa461b71c4cfcf");
        gson = new Gson();
        String str = gson.toJson(hashMap).toString();
        map.put("authInfo", str);
        String value = gson.toJson(map).toString();
        mapData = new HashMap<>();
        mapData.put("parameters", value);
    }

    //抽屉
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


        NetHelper.MyRequest(URLValues.RECOMMEND_URL, RecommendLvBean.class, new NetListener<RecommendLvBean>() {
            @Override
            public void successListener(RecommendLvBean response) {
                lvAdapter = new RecommendLvAdapter(getContext());
                list = response.getData();
                lvAdapter.setList(list);
                lv.setAdapter(lvAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, mapData);
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

    //下拉刷新
    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                NetHelper.MyRequest(URLValues.RECOMMEND_URL, RecommendLvBean.class, new NetListener<RecommendLvBean>() {

                    @Override
                    public void successListener(RecommendLvBean response) {
                        list = response.getData();
                        lvAdapter.setList(list);
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, mapData);
            }
            //数字代表2秒后加载数据
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {




            @Override
            public void run() {

                RecommendMap();

                swipeToLoadLayout.setLoadingMore(false);
                NetHelper.MyRequest(URLValues.RECOMMEND_URL, RecommendLvBean.class, new NetListener<RecommendLvBean>() {
                    @Override
                    public void successListener(RecommendLvBean response) {
                        list.addAll(response.getData());
                        lvAdapter.setList(list);
                        bodyNum += 16;
                        map.put("bodyNnm", String.valueOf(bodyNum));
                        String value = gson.toJson(map).toString();
                        Log.d("RecommendFragment", map.get("bodyNnm"));
                        mapData.put("parameters", value);
                        RecommendMap();
                        Log.d("RecommendFragment", "response.getData():" + response.getData());
                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }, mapData);
            }
        }, 2000);
    }

    //自动更新
    private void autoRefresh() {
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        });
    }

}
