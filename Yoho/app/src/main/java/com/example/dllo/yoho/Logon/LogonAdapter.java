package com.example.dllo.yoho.Logon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/5.
 */

public class LogonAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> arrayList;

    public LogonAdapter setArrayList(ArrayList<Fragment> arrayList) {
        this.arrayList = arrayList;
        return this;
    }

    public LogonAdapter(FragmentManager fm) {
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
}
