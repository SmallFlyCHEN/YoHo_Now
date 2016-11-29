package com.example.dllo.yoho.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */

public class RecommendLvAdapter extends BaseAdapter{

    List<RecommendLvBean.DataBean> list;
    Context context;

    public static final int ONE= 1;
    public static final int TWO= 2;
    public static final int THREE = 3;
    public static final int COUNT = 4;
    private SimpleDateFormat sdf;

    public RecommendLvAdapter(Context context) {
        this.context = context;
    }

    public RecommendLvAdapter setList(List<RecommendLvBean.DataBean> list) {
        this.list = list;
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        if (type == 0){
            return ONE;
        }else if (type == 2){
            return TWO;
        }else {
            return THREE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
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
        sdf = new SimpleDateFormat("MM.dd.yyyy");
        MyViewHolderOne viewHolderOne = null;
        MyViewHolderTwo viewHolderTwo = null;
        MyViewHolderThree viewHolderThree = null;
        if (convertView == null){
            switch (getItemViewType(position)){
                case ONE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_one, parent, false);
                    viewHolderOne = new MyViewHolderOne(convertView);
                    convertView.setTag(viewHolderOne);
                    break;
                case TWO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_two, parent, false);
                    viewHolderTwo = new MyViewHolderTwo(convertView);
                    convertView.setTag(viewHolderTwo);
                    break;
                case THREE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_three, parent, false);
                    break;
            }
        }else {
            switch (getItemViewType(position)){
                case ONE:
                    viewHolderOne = (MyViewHolderOne) convertView.getTag();
                    break;
                case TWO:
                    viewHolderTwo = (MyViewHolderTwo) convertView.getTag();
                    break;
                case THREE:
                    break;
            }
        }
        switch (getItemViewType(position)){
            case ONE:
                Picasso.with(context).load(list.get(position).getParams().get(0).getImage()).into(viewHolderOne.oneIv);
                viewHolderOne.oneTvTitle.setText(list.get(position).getParams().get(0).getTitle());
                viewHolderOne.oneTvTagName.setText("#" + list.get(position).getParams().get(0).getTag().get(0).getTag_name());
                Date date = new Date(Long.valueOf(list.get(position).getParams().get(0).getCreate_time()));
                String time = sdf.format(date);
                viewHolderOne.oneTvCreateTime.setText(time);
                Picasso.with(context).load(list.get(position).getParams().get(1).getImage()).into(viewHolderOne.twoIv);
                viewHolderOne.twoTvTitle.setText(list.get(position).getParams().get(1).getTitle());
                viewHolderOne.twoTvTagName.setText("#" + list.get(position).getParams().get(1).getTag().get(0).getTag_name());
                Date date1 = new Date(Long.valueOf(list.get(position).getParams().get(1).getCreate_time()));
                String time1 = sdf.format(date1);
                viewHolderOne.twoTvCreateTime.setText(time1);
                Picasso.with(context).load(list.get(position).getParams().get(2).getImage()).into(viewHolderOne.threeIv);
                viewHolderOne.threeTvTitle.setText(list.get(position).getParams().get(2).getTitle());
                viewHolderOne.threeTvTagName.setText("#" + list.get(position).getParams().get(2).getTag().get(0).getTag_name());
                Date date2 = new Date(Long.valueOf(list.get(position).getParams().get(2).getCreate_time()));
                String time2 = sdf.format(date2);
                viewHolderOne.threeTvCreateTime.setText(time2);
                break;
            case TWO:
                Picasso.with(context).load(list.get(position).getParams().get(0).getImage()).into(viewHolderTwo.itemTwoIv);
                viewHolderTwo.itemTwoTvTitle.setText(list.get(position).getParams().get(0).getTitle());
                viewHolderTwo.itemTwoTvTagName.setText("#" + list.get(position).getParams().get(0).getTag().get(0).getTag_name());
                Date date3 = new Date(Long.valueOf(list.get(position).getParams().get(0).getCreate_time()));
                String time3 = sdf.format(date3);
                viewHolderTwo.itemTwoCreateTime.setText(time3);
                break;
            case THREE:
                break;
        }
        return convertView;
    }

    private class MyViewHolderOne {

        private final ImageView oneIv;
        private final TextView oneTvTitle;
        private final TextView oneTvTagName;
        private final TextView oneTvCreateTime;
        private final ImageView twoIv;
        private final TextView twoTvTitle;
        private final TextView twoTvTagName;
        private final TextView twoTvCreateTime;
        private final ImageView threeIv;
        private final TextView threeTvTitle;
        private final TextView threeTvTagName;
        private final TextView threeTvCreateTime;

        public MyViewHolderOne(View view) {
            oneIv = (ImageView) view.findViewById(R.id.recommend_one_iv_image);
            oneTvTitle = (TextView) view.findViewById(R.id.recommend_one_tv_title);
            oneTvTagName = (TextView) view.findViewById(R.id.recommend_one_tv_tag_name);
            oneTvCreateTime = (TextView) view.findViewById(R.id.recommend_one_tv_create_time);
            twoIv = (ImageView) view.findViewById(R.id.recommend_two_iv_image);
            twoTvTitle = (TextView) view.findViewById(R.id.recommend_two_tv_title);
            twoTvTagName = (TextView) view.findViewById(R.id.recommend_two_tv_tag_name);
            twoTvCreateTime = (TextView) view.findViewById(R.id.recommend_two_tv_create_time);
            threeIv = (ImageView) view.findViewById(R.id.recommend_three_iv_image);
            threeTvTitle = (TextView) view.findViewById(R.id.recommend_three_tv_title);
            threeTvTagName = (TextView) view.findViewById(R.id.recommend_three_tv_tag_name);
            threeTvCreateTime = (TextView) view.findViewById(R.id.recommend_three_tv_create_time);
        }
    }

    private class MyViewHolderTwo {

        private final ImageView itemTwoIv;
        private final TextView itemTwoTvTitle;
        private final TextView itemTwoTvTagName;
        private final TextView itemTwoCreateTime;

        public MyViewHolderTwo(View view) {
            itemTwoIv = (ImageView) view.findViewById(R.id.recommend_item_two_iv_image);
            itemTwoTvTitle = (TextView) view.findViewById(R.id.recommend_item_two_tv_title);
            itemTwoTvTagName = (TextView) view.findViewById(R.id.recommend_item_two_tv_tag_name);
            itemTwoCreateTime = (TextView) view.findViewById(R.id.recommend_item_two_tv_create_time);
        }
    }

    private class MyViewHolderThree {
    }
}
