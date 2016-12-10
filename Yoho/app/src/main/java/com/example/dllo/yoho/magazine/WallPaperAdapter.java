package com.example.dllo.yoho.magazine;

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
 * Created by dllo on 16/11/26.
 */

public class WallPaperAdapter extends BaseAdapter{

    List<WallpaperBean.DataBean.WallpaperListBean> list;
    Context context;

    public WallPaperAdapter(Context context) {
        this.context = context;
    }

    public WallPaperAdapter setList(List<WallpaperBean.DataBean.WallpaperListBean> list) {
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
        WallPaperViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_wallpaper, parent, false);
            viewHolder = new WallPaperViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (WallPaperViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(list.get(position).getImages().get(0).getThumbImage()).into(viewHolder.zeroIv);
        Picasso.with(context).load(list.get(position).getImages().get(1).getThumbImage()).into(viewHolder.oneIv);
        Picasso.with(context).load(list.get(position).getImages().get(2).getThumbImage()).into(viewHolder.twoIv);
        if (list.get(position).getImages().size() == 4){
            Picasso.with(context).load(list.get(position).getImages().get(3).getThumbImage()).into(viewHolder.threeIv);
        }
        viewHolder.journal.setText(list.get(position).getJournal());
        viewHolder.month.setText(list.get(position).getMonth());
        return convertView;
    }

    private class WallPaperViewHolder {

        final private TextView journal;
        final private TextView month;
        final private ImageView zeroIv, oneIv, twoIv, threeIv;
        public WallPaperViewHolder(View view) {
            journal = (TextView) view.findViewById(R.id.wallpaper_tv_journal);
            month = (TextView) view.findViewById(R.id.wallpaper_tv_month);
            zeroIv = (ImageView) view.findViewById(R.id.wallpaper_iv_thumbImage_zero);
            oneIv = (ImageView) view.findViewById(R.id.wallpaper_iv_thumbImage_one);
            twoIv = (ImageView) view.findViewById(R.id.wallpaper_iv_thumbImage_two);
            threeIv = (ImageView) view.findViewById(R.id.wallpaper_iv_thumbImage_three);
        }
    }
}
