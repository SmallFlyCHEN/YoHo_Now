package com.example.dllo.yoho.column.columthree;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.dllo.yoho.R;
import com.example.dllo.yoho.base.BaseActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/12/10.
 */

public class ColumnThreeActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String url;
    private ImageView back;
    private ImageView share;
    private ImageView icon;

    @Override
    protected int setLayout() {
        return R.layout.activity_columnthree;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        webView = (WebView) findViewById(R.id.columnthree_wb);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        back = (ImageView) findViewById(R.id.column_back);
        share = (ImageView) findViewById(R.id.column_share);
        icon = (ImageView) findViewById(R.id.column_icon);

        back.setOnClickListener(this);
        share.setOnClickListener(this);
        icon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.column_back:
                finish();
                break;
            case R.id.column_share:
                showShare();
                break;
            case R.id.column_icon:
                break;
        }
    }

    //第三方分享
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
