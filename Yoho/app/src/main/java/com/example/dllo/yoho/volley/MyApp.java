package com.example.dllo.yoho.volley;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/29.
 */

public class MyApp extends Application{
    //切记如何使用!!!! 在清单文件中加入自己的App
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    //对外提供一共获取Context对象的方法
    public static Context getContext(){
        return context;
    }
}
