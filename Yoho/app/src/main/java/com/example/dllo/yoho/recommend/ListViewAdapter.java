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

/**
 * Created by dllo on 16/12/1.
 */

public class ListViewAdapter extends BaseAdapter{

    private LvBean bean;
    Context context;

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    public ListViewAdapter setBean(LvBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getContent().size();
    }

    @Override
    public Object getItem(int position) {
        return bean == null ? null : bean.getData().getContent().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.getData().getContent().get(position).getImage()).into(viewHolder.image);
        viewHolder.title.setText(bean.getData().getContent().get(position).getTitle());
        viewHolder.tagName.setText("#" + bean.getData().getContent().get(position).getTag().get(0).getTag_name());
        viewHolder.createTime.setText(bean.getData().getContent().get(position).getCreate_time());
        return convertView;
    }

    private class MyViewHolder {

        private final ImageView image;
        private final TextView title;
        private final TextView tagName;
        private final TextView createTime;

        public MyViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.search_iv_image);
            title = (TextView) view.findViewById(R.id.search_tv_title);
            tagName = (TextView) view.findViewById(R.id.search_tv_tag_name);
            createTime = (TextView) view.findViewById(R.id.search_tv_create_time);
        }
    }
}
