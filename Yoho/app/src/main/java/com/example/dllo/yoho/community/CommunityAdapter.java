package com.example.dllo.yoho.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.yoho.R;

import java.util.List;

/**
 * Created by dllo on 16/11/26.
 */

public class CommunityAdapter extends BaseAdapter{

    public static final int ONE= 1;
    public static final int TWO= 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int COUNT = 5;
    List<CommunityBean.DataBean.ListBean> list;
    Context context;

    public CommunityAdapter(Context context) {
        this.context = context;
    }

    public CommunityAdapter setList(List<CommunityBean.DataBean.ListBean> list) {
        this.list = list;
        return this;
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        //获取集合中的个数
        int a = list.get(position).getBlocks().size();
        //获取集合中第一个的类型
        String str = list.get(position).getBlocks().get(0).getTemplateKey();
        //定义类型
        String type = "text";
        if (a == 1){
            return ONE;
        }else if (a == 3){
            return THREE;
        }else if (a == 2 && str == type){
            return TWO;
        }else {
            return FOUR;
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommunityOneViewHolder oneViewHolder = null;
        CommunityTwoViewHolder twoViewHolder = null;
        CommunityThreeViewHolder threeViewHolder = null;
        CommunityFourViewHolder fourViewHolder = null;
        if (convertView == null){
            switch (getItemViewType(position)){
                case ONE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_community_one, parent, false);
                    oneViewHolder = new CommunityOneViewHolder(convertView);
                    break;
                case TWO:
                    break;
                case THREE:
                    break;
                case FOUR:
                    break;
            }
        }

        return convertView;
    }

    private class CommunityOneViewHolder {
        public CommunityOneViewHolder(View view) {

        }
    }

    private class CommunityTwoViewHolder {
    }

    private class CommunityThreeViewHolder {
    }

    private class CommunityFourViewHolder {
    }
}
