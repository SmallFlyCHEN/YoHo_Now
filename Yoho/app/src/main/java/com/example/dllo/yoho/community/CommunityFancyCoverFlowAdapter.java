package com.example.dllo.yoho.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.dalong.francyconverflow.FancyCoverFlowAdapter;
import com.example.dllo.yoho.R;

/**
 * Created by dllo on 16/12/3.
 */

public class CommunityFancyCoverFlowAdapter extends FancyCoverFlowAdapter{

    private Context context;
    private CommunityFancyCoverFlowBean fancyCoverFlowBean;

    public CommunityFancyCoverFlowAdapter(Context context) {
        this.context = context;
    }

    public CommunityFancyCoverFlowAdapter setFancyCoverFlowBean(CommunityFancyCoverFlowBean fancyCoverFlowBean) {
        this.fancyCoverFlowBean = fancyCoverFlowBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public View getCoverFlowItem(int position, View reusableView, ViewGroup parent) {
        FcfViewHolder viewHolder = null;
        if (reusableView == null){
            reusableView = LayoutInflater.from(context).inflate(R.layout.item_community_header_fcf, parent, false);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = windowManager.getDefaultDisplay().getWidth() + 600;
            reusableView.setLayoutParams(new FancyCoverFlow.LayoutParams(width / 3, FancyCoverFlow.LayoutParams.WRAP_CONTENT));
            viewHolder = new FcfViewHolder();
            viewHolder.linearLayout = (LinearLayout) reusableView.findViewById(R.id.item_community_ll);
            viewHolder.ivForumPic = (ImageView) reusableView.findViewById(R.id.item_community_iv_forumPic);
            viewHolder.ivHeadIconOne = (ImageView) reusableView.findViewById(R.id.item_community_iv_headIcon_one);
            viewHolder.ivHeadIconTwo = (ImageView) reusableView.findViewById(R.id.item_community_iv_headIcon_two);
            viewHolder.tvForumName = (TextView) reusableView.findViewById(R.id.community_one_tv_forumName);
            viewHolder.tvPostsNum = (TextView) reusableView.findViewById(R.id.item_community_tv_postsNum);
            viewHolder.tvCommentsNum = (TextView) reusableView.findViewById(R.id.item_community_tv_commentsNum);
            viewHolder.tvPraiseNum = (TextView) reusableView.findViewById(R.id.item_community_tv_praiseNum);
            viewHolder.tvHotPostPostsTitle = (TextView) reusableView.findViewById(R.id.item_community_tv_hotPost_postsTitle);
            viewHolder.tvNewPostPostsTitle = (TextView) reusableView.findViewById(R.id.item_community_tv_newPost_postsTitle);
            viewHolder.tvOneDayAddNum = (TextView) reusableView.findViewById(R.id.item_community_tv_oneDayAddNum);
            reusableView.setTag(viewHolder);
        }else {
            viewHolder = (FcfViewHolder) reusableView.getTag();
        }
        //判断位置是否是第0个, 位置是否是最后一个
        if (position == 0){
            viewHolder.linearLayout.setBackgroundResource(R.mipmap.community_home_forumlist_stay_tuned);
        }else if (position == fancyCoverFlowBean.getData().getForumInfo().size() + 1){
            viewHolder.linearLayout.setBackgroundResource(R.mipmap.community_home_forumlist_stay_tuned);
        }else {
            Glide.with(context).load(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getForumPic())
                    .into(viewHolder.ivForumPic);
            Glide.with(context).load(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getHotPost().getUser()
                    .getHeadIcon()).into(viewHolder.ivHeadIconOne);
            Glide.with(context).load(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getNewPost().getUser()
                    .getHeadIcon()).into(viewHolder.ivHeadIconTwo);
            viewHolder.tvForumName.setText(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getForumName());
            viewHolder.tvPostsNum.setText("帖子" + String.valueOf(fancyCoverFlowBean.getData().getForumInfo()
                    .get(position - 1 ).getForumName()));
            viewHolder.tvCommentsNum.setText("回复" + String.valueOf(fancyCoverFlowBean.getData().getForumInfo()
                    .get(position - 1).getCommentsNum()));
            viewHolder.tvPraiseNum.setText("赞" + String.valueOf(fancyCoverFlowBean.getData().getForumInfo().get(position - 1)
                    .getPraiseNum()));
            viewHolder.tvHotPostPostsTitle.setText(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getHotPost().getPostsTitle());
            viewHolder.tvNewPostPostsTitle.setText(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getNewPost().getPostsTitle());
            viewHolder.tvOneDayAddNum.setText(fancyCoverFlowBean.getData().getForumInfo().get(position - 1).getOneDayAddNum() + "条更新>");
        }
        return reusableView;
    }

    @Override
    public int getCount() {
        return fancyCoverFlowBean.getData().getForumInfo().size() + 2;
    }

    @Override
    public Object getItem(int position) {
        return fancyCoverFlowBean.getData().getForumInfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //找不到view对象所以不能findViewById找到对应的Id
    private static class FcfViewHolder {
        ImageView ivForumPic, ivHeadIconOne, ivHeadIconTwo;
        TextView tvForumName, tvPostsNum, tvCommentsNum, tvPraiseNum, tvHotPostPostsTitle, tvNewPostPostsTitle, tvOneDayAddNum;
        LinearLayout linearLayout;
    }
}
