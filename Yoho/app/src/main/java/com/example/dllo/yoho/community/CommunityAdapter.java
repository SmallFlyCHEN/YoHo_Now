package com.example.dllo.yoho.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.yoho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/11/26.
 */

public class CommunityAdapter extends BaseAdapter{

    List<CommunityBean.DataBean.ListBean> list;
    Context context;
    private ArrayList<String> url;
    public static final int URL_ONE = 1;
    public static final int URL_TWO = 2;
    private String oneUrl;
    private String twoUrl;
    private String threeUrl;

    public CommunityAdapter(Context context) {
        this.context = context;
    }

    public CommunityAdapter setList(List<CommunityBean.DataBean.ListBean> list) {
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

        CommunityViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_community, parent, false);
            viewHolder = new CommunityViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (CommunityViewHolder) convertView.getTag();
        }

        url = new ArrayList<>();
        int type = list.get(position).getBlocks().size();
        Picasso.with(context).load(getURLString(list.get(position).getAuthorInfo().getHeadIcon()))
                .into(viewHolder.handIcon);
        if (list.get(position).getPostsTitle() != null){
            viewHolder.postsTitle.setText(list.get(position).getPostsTitle());
        }else {

        }
        viewHolder.createTime.setText(String.valueOf(list.get(position).getCreateTime()));
        viewHolder.comment.setText(String.valueOf(list.get(position).getComment()));
        viewHolder.praise.setText(String.valueOf(list.get(position).getPraise()));
        viewHolder.nickname.setText(list.get(position).getAuthorInfo().getNickName());
        viewHolder.forumName.setText(list.get(position).getForumName());

        for (int i = 0; i < type; i++) {
            if (list.get(position).getBlocks().get(i).getTemplateKey().equals("text")){
                if (list.get(position).getBlocks().get(i).getContentData() != null){
                    viewHolder.contentData.setText(list.get(position).getBlocks().get(i).getContentData());
                }else {
                    viewHolder.contentData.setVisibility(View.GONE);
                }
            }else {
                url.add(getURLString(list.get(position).getBlocks().get(i).getContentData()));
            }
        }

        if (url.size() == URL_ONE){
            oneUrl = url.get(0);
            viewHolder.twoIv.setVisibility(View.GONE);
            viewHolder.threeIv.setVisibility(View.GONE);
            Picasso.with(context).load(oneUrl).into(viewHolder.oneIv);
        }else if (url.size() == URL_TWO){
            oneUrl = url.get(0);
            twoUrl = url.get(1);
            viewHolder.twoIv.setVisibility(View.VISIBLE);
            viewHolder.threeIv.setVisibility(View.INVISIBLE);
            Picasso.with(context).load(oneUrl).into(viewHolder.oneIv);
            Picasso.with(context).load(twoUrl).into(viewHolder.twoIv);
        }else {
            oneUrl = url.get(0);
            twoUrl = url.get(1);
            threeUrl = url.get(2);
            Picasso.with(context).load(oneUrl).into(viewHolder.oneIv);
            Picasso.with(context).load(twoUrl).into(viewHolder.twoIv);
            Picasso.with(context).load(threeUrl).into(viewHolder.threeIv);
            viewHolder.twoIv.setVisibility(View.VISIBLE);
            viewHolder.threeIv.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private class CommunityViewHolder {

        private final CircleImageView handIcon;
        private final TextView nickname;
        private final TextView createTime;
        private final TextView postsTitle;
        private final TextView contentData;
        private final ImageView oneIv;
        private final ImageView twoIv;
        private final ImageView threeIv;
        private final TextView forumName;
        private final TextView comment;
        private final TextView praise;

        public CommunityViewHolder(View view) {
            handIcon = (CircleImageView) view.findViewById(R.id.community_one_iv_handIcon);
            nickname = (TextView) view.findViewById(R.id.community_one_tv_nickname);
            createTime = (TextView) view.findViewById(R.id.community_one_tv_createTime);
            postsTitle = (TextView) view.findViewById(R.id.community_one_tv_postsTitle);
            contentData = (TextView) view.findViewById(R.id.community_one_tv_contentData);
            oneIv = (ImageView) view.findViewById(R.id.community_one_iv_contentData_one);
            twoIv = (ImageView) view.findViewById(R.id.community_one_iv_contentData_two);
            threeIv = (ImageView) view.findViewById(R.id.community_one_iv_contentData_three);
            forumName = (TextView) view.findViewById(R.id.community_one_tv_forumName);
            comment = (TextView) view.findViewById(R.id.community_one_tv_comment);
            praise = (TextView) view.findViewById(R.id.community_one_tv_praise);
        }
    }

    public static final String getURLString(String str){
        String urls = str.substring(0,str.indexOf("?"));
        return urls;
    }
}
