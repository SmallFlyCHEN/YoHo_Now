package com.example.dllo.yoho.magazine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class MagazineAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> arrayList;
    String[] str = {"杂志", "壁纸"};

    public MagazineAdapter setArrayList(ArrayList<Fragment> arrayList) {
        this.arrayList = arrayList;
        return this;
    }

    public MagazineAdapter(FragmentManager fm) {
        super(fm);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
