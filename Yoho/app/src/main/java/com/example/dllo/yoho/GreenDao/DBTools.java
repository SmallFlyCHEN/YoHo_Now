package com.example.dllo.yoho.GreenDao;

import com.example.dllo.yoho.volley.MyApp;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/9.
 */

public class DBTools {

    private static DBTools ourInstance = new DBTools();
    private static CollectBeanDao collectBeanDao;

    //对外提供 getOurInstance方法获取本类的单例对象
    public static DBTools getCollectBeanDao() {
        if (ourInstance == null){
            synchronized (DBTools.class){
                if (ourInstance == null){
                    ourInstance = new DBTools();
                }
            }
        }
        //初始化collectBeanDao对象
        collectBeanDao = MyApp.getDaoSession().getCollectBeanDao();
        return ourInstance;
    }

    public DBTools() {
    }

    public void insertList(List<CollectBean> list){
        collectBeanDao.insertInTx(list);
    }

    public void insertCollect(CollectBean CollectBean){
        collectBeanDao.insert(CollectBean);
    }



    public List<CollectBean> queryAll(){
        List<CollectBean> list = collectBeanDao.loadAll();
        return list;
    }

    public void deleteAll(){
        collectBeanDao.deleteAll();
    }

    public boolean isSave(String boty){
        QueryBuilder<CollectBean> queryBuilder = collectBeanDao.queryBuilder().where(CollectBeanDao.Properties.Boty.eq(boty));
        Long size = queryBuilder.count();
        return size > 0 ? true : false;
    }
}
