package com.example.dllo.yoho.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by dllo on 16/11/29.
 */

public class NetHelper {

    private RequestQueue requestQueue;

    private static NetHelper ourInstance = new NetHelper();

    //在单例模式中我们对外提供了一个方法来获取对象
    public static NetHelper getInstance(){
        return ourInstance;
    }

    //为什么要把构造方法私有化,
    //不想让别人创建对象, 所有私有化 我们只使用对外的方法获取本类的对象
    private NetHelper(){
        getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        //第一个判断是用于提高代码消息的, 如果多个地方使用了这个单列, 那么第一层判断就可以直接创建队列, 不为空的情况下就直接返回队列
        if (requestQueue == null){
            //加锁 保证线程不会拥挤阻塞
            synchronized (NetHelper.class){
                //如果队列为空 创建我们自己的队列
                if (requestQueue == null){
                    requestQueue = Volley.newRequestQueue(MyApp.getContext());
                }
            }
        }
        return requestQueue;
    }

    //私有化方法
    //这是最基本的请求方式
    private <T> void baseRequest(Request<T> request){
        requestQueue.add(request);
    }
    private <T> void baseGsonRequest(String url, Class<T> mClass, final NetListener<T> listener){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        }, mClass);
        requestQueue.add(gsonRequest);
    }

    private <T> void baseGsonPostRequset(String url, Class<T> mClass, final NetListener<T> listener, HashMap<String, String> map){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        }, mClass, map);
        requestQueue.add(gsonRequest);
    }

    private void baseStringRequest(String url, final NetListener<String> listener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        });
        requestQueue.add(stringRequest);
    }

    //对外提供的方法
    //没有实体类的解析方法
    public static void MySRequest(String url, NetListener<String> listener){
        getInstance().baseStringRequest(url, listener);
    }

    //get解析方法
    public static <T> void MyRequest(String url,Class<T> mClass,NetListener<T> listener){
        getInstance().baseGsonRequest(url,mClass,listener);
    }

    //post解析方法
    public static <T> void MyRequest(String url, Class<T> mClass, NetListener<T> listener, HashMap<String, String> map){
        getInstance().baseGsonPostRequset(url, mClass, listener, map);
    }
}
