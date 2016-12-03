package com.example.dllo.yoho.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by dllo on 16/12/1.
 */

//复用fragment时更改适配器(将fragment销毁)
public class SearchAdapter extends FragmentStatePagerAdapter{

    private static SearchBean bean;

    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setBean(SearchBean bean){
        this.bean = bean;
        notifyDataSetChanged();
    }
    //根据不同的position加载不同的数据
    @Override
    public Fragment getItem(int position) {
        return SearchFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return bean != null ? bean.getData().get(0).getSubNav().size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bean.getData().get(0).getSubNav().get(position).getChannel_name_cn();
    }

    //把从集合中获取的数据(写个静态方法)
    public static String getMessage(int position){
        return bean.getData().get(0).getSubNav().get(position).getId();
    }
}
