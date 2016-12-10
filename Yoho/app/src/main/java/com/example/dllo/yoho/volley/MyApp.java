package com.example.dllo.yoho.volley;

import android.app.Application;
import android.content.Context;

import com.example.dllo.yoho.GreenDao.DaoMaster;
import com.example.dllo.yoho.GreenDao.DaoSession;

/**
 * Created by dllo on 16/11/29.
 */

public class MyApp extends Application{
    //切记如何使用!!!! 在清单文件中加入自己的App
    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    //对外提供一共获取Context对象的方法
    public static Context getContext(){
        return context;
    }

    //对外提供获取DaoMaster对象
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "Collect.db", null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    //对外提供获取DaoSession对象
    public static DaoSession getDaoSession() {
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
        }
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
