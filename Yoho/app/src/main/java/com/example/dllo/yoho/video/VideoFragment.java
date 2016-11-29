package com.example.dllo.yoho.video;

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

public class VideoFragment extends BaseFragment{

    private ViewPager vp;
    private TabLayout tab;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        vp = (ViewPager) view.findViewById(R.id.video_vp);
        tab = (TabLayout) view.findViewById(R.id.video_tab);
    }

    //初始化数据
    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new TabVideoFragment());
        arrayList.add(new DirectSeedingFragment());

        VideoAdapter videoAdapter = new VideoAdapter(getChildFragmentManager());
        videoAdapter.setArrayList(arrayList);
        vp.setAdapter(videoAdapter);
        tab.setupWithViewPager(vp);
    }
}
