package com.example.dllo.yoho.column;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.MyViewHolder>{

    List<ColumnBean.DataBean> list;
    Context context;

    public ColumnAdapter(Context context) {
        this.context = context;
    }

    public ColumnAdapter setList(List<ColumnBean.DataBean> list) {
        this.list = list;
        return this;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_column, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getCover()).into(holder.columnIv);
        holder.columnTvTitle.setText(list.get(position).getTitle());
        holder.columnTvSummary.setText(list.get(position).getSummary());
        holder.columnTvTotal.setText(list.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView columnIv;
        private final TextView columnTvTitle;
        private final TextView columnTvSummary;
        private final TextView columnTvTotal;

        public MyViewHolder(View itemView) {
            super(itemView);
            columnIv = (ImageView) itemView.findViewById(R.id.column_iv_cover);
            columnTvTitle = (TextView) itemView.findViewById(R.id.column_tv_title);
            columnTvSummary = (TextView) itemView.findViewById(R.id.column_tv_summary);
            columnTvTotal = (TextView) itemView.findViewById(R.id.column_tv_total);
        }
    }
}
