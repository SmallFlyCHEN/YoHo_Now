package com.example.dllo.yoho.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */

public class TabVideoAdapter extends BaseAdapter {

    List<TabVideoBean.DataBean.ContentBean> list;
    Context context;

    public TabVideoAdapter(Context context) {
        this.context = context;
    }

    public TabVideoAdapter setList(List<TabVideoBean.DataBean.ContentBean> list) {
        this.list = list;
        return this;
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
        TabViewHolder tabViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_tab_video, parent, false);
            tabViewHolder = new TabViewHolder(convertView);
            convertView.setTag(tabViewHolder);
        }else {
            tabViewHolder = (TabViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getImage()).into(tabViewHolder.tabVideoIv);
        tabViewHolder.tabVideoTvTitle.setText(list.get(position).getTitle());
        tabViewHolder.tabVideoTvTagName.setText("#" + list.get(position).getTag().get(0).getTag_name());
        tabViewHolder.tabVideoTvCreateTime.setText(list.get(position).getCreate_time());
        return convertView;
    }

    private class TabViewHolder {

        private final ImageView tabVideoIv;
        private final TextView tabVideoTvTitle;
        private final TextView tabVideoTvTagName;
        private final TextView tabVideoTvCreateTime;

        public TabViewHolder(View view) {
            tabVideoIv = (ImageView) view.findViewById(R.id.tab_video_image);
            tabVideoTvTitle = (TextView) view.findViewById(R.id.tab_video_title);
            tabVideoTvTagName = (TextView) view.findViewById(R.id.tab_video_tag_name);
            tabVideoTvCreateTime = (TextView) view.findViewById(R.id.tab_video_create_time);
        }
    }
}
