package com.example.dllo.yoho.column.columntwo;

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
 * Created by dllo on 16/12/9.
 */

public class ColumnTwoAdapter extends BaseAdapter{

    Context context;
    ColumnTwoBean columnTwoBean;

    public ColumnTwoAdapter(Context context) {
        this.context = context;
    }

    public ColumnTwoAdapter setColumnTwoBean(ColumnTwoBean columnTwoBean) {
        this.columnTwoBean = columnTwoBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return columnTwoBean == null ? 0 : columnTwoBean.getData().getContent().size();
    }

    @Override
    public Object getItem(int position) {
        return columnTwoBean.getData().getContent().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_column_two, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(columnTwoBean.getData().getContent().get(position).getImage()).into(holder.image);
        holder.title.setText(columnTwoBean.getData().getContent().get(position).getTitle());
        holder.tag.setText("#"+columnTwoBean.getData().getContent().get(position).getTag().get(0).getTag_name());
        holder.time.setText(columnTwoBean.getData().getContent().get(position).getCreate_time());
        return convertView;
    }

    private class MyViewHolder {

        private final ImageView image;
        private final TextView title;
        private final TextView tag;
        private final TextView time;

        public MyViewHolder(View view) {
            image = (ImageView)view.findViewById(R.id.column_two_Image);
            title = (TextView) view.findViewById(R.id.column_two_title);
            tag = (TextView) view.findViewById(R.id.column_two_tag_name);
            time = (TextView) view.findViewById(R.id.column_two_create_time);
        }
    }
}
