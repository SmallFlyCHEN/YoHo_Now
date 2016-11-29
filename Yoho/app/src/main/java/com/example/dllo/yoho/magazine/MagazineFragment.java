package com.example.dllo.yoho.magazine;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.yoho.R;
import com.example.dllo.yoho.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/23.
 */

public class MagazineFragment extends BaseFragment{

    private TabLayout tab;
    private ViewPager vp;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_magazine;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.magazine_tab);
        vp = (ViewPager) view.findViewById(R.id.magazine_vp);
    }

    //初始化数据
    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new NewMagazineFragment());
        arrayList.add(new WallpaperFragment());

        MagazineAdapter adapter = new MagazineAdapter(getChildFragmentManager());
        adapter.setArrayList(arrayList);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }
}
