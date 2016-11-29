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

public class DirectSeedingAdapter extends BaseAdapter{
    List<DirectSeedingBean.DataBean.ContentBean> list;
    Context context;

    public DirectSeedingAdapter(Context context) {
        this.context = context;
    }

    public DirectSeedingAdapter setList(List<DirectSeedingBean.DataBean.ContentBean> list) {
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
        DsViewHolder dsViewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_direct_seeding_lv, parent, false);
            dsViewHolder = new DsViewHolder(convertView);
            convertView.setTag(dsViewHolder);
        }else {
            dsViewHolder = (DsViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getImage()).into(dsViewHolder.dsIv);
        dsViewHolder.dsTvTitle.setText(list.get(position).getTitle());
        dsViewHolder.dsTvTagName.setText("#" + list.get(position).getTag().get(0).getTag_name());
        dsViewHolder.dsTvCreateTime.setText(list.get(position).getCreate_time());
        return convertView;
    }

    private class DsViewHolder {

        private final ImageView dsIv;
        private final TextView dsTvTitle;
        private final TextView dsTvCreateTime;
        private final TextView dsTvTagName;

        public DsViewHolder(View view) {
            dsIv = (ImageView) view.findViewById(R.id.direct_seeding_image);
            dsTvTitle = (TextView) view.findViewById(R.id.direct_seeding_title);
            dsTvCreateTime = (TextView) view.findViewById(R.id.direct_seeding_create_time);
            dsTvTagName = (TextView) view.findViewById(R.id.direct_seeding_tag_name);
        }
    }
}
