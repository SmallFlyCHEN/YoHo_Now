package com.example.dllo.yoho.mymagazine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yoho.GreenDao.CollectBean;
import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/9.
 */

public class GridViewAdapter extends BaseAdapter{

    Context context;
    ArrayList<CollectBean> collectBeanList;

    public GridViewAdapter setCollectBeanList(ArrayList<CollectBean> collectBeanList) {
        this.collectBeanList = collectBeanList;
        notifyDataSetChanged();
        return this;
    }

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return collectBeanList == null ? 0 : collectBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv.setText(collectBeanList.get(position).getBoty());
        Picasso.with(context).load(collectBeanList.get(position).getImg()).into(holder.iv);
        return convertView;
    }

    private class MyViewHolder {

        private final ImageView iv;
        private final TextView tv;

        public MyViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.item_grid_iv);
            tv = (TextView) view.findViewById(R.id.item_grid_tv);
        }
    }
}
