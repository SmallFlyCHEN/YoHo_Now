package com.example.dllo.yoho;

/**
 * Created by dllo on 16/11/22.
 */
public class URLValues {

    //栏目一级界面数据
    public static final String COLUMN_URl = "http://new.yohoboys.com/yohoboyins/v5/channel/aggregationIndex";
    //推荐一级界面数据
    public static final String RECOMMEND_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/recommendList";
    //推荐界面轮播图数据
    public static final String CAROUSEL_URl = "http://new.yohoboys.com/yohoboyins/v5/channel/newBanner";
    //视频一级界面(VIDEO)数据
    public static final String TABVIDEO_URL = "http://new.yohoboys.com/yohoboyins/v5/media/videoList";
    //视频一级界面(直播)数据
    public static final String DIRECTSEEDING_URL = "http://new.yohoboys.com/yohoboyins/v5/media/qcloudList";
    //杂志界面第一行数据
    public static final String NEWMAGAZINE_ONE_URL = "http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=" +
            "0&lastTime=1479786199&magCount=3&magType=1&width=1080&height=1776&ppi=480&num=3";
    //杂志界面第二行数据
    public static final String NEWMAGAZINE_TWO_URL = "http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=" +
            "0&lastTime=1479786199&magCount=3&magType=3&width=1080&height=1776&ppi=480&num=3";
    //杂志界面第三行数据
    public static final String NEWMAGAZINE_THREE_URL = "http://h5api.myoho.net/index.php?r=Apiemag/magList&startTime=" +
            "0&lastTime=1479784306&magCount=3&magType=5&width=1080&height=1776&ppi=480&num=3";
    //杂志界面(壁纸)
    public static final String WALLPAPER_URL = "http://h5api.myoho.net/index.php?r=Apiemag/getWallpaperListV4&start=" +
            "0&end=200&device=3&scale=2\n";
    //搜索键二级界面的TabLayout标题
    public static final String SEARCHTAB_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/navigation";
    //搜索键二级界面ViewPager的数据
    public static final String SEARCHCOMMNOL_URL = "http://new.yohoboys.com/yohoboyins/v5/channel/contentList";
    //社区中的数据(10条)
    public static final String COMMUNITY_URL = "http://social.yoho.cn/social?app_version=5.0.4&client_secret=" +
            "2e515454baad66272556819822cb0bbe&client_type=android&lastedTime=0&limit=10&method=" +
            "app.social.getHomePagePostList&os_version=" +
            "android5.1%3AGoogle_Nexus_5_-_5.1.0_-_API_22_-_1080x1920&screen_size=1080x1776&v=7";
    //社区中的第二个能滑动的数据
    public static final String COMMUNITY_FCF_URL = "http://social.yoho.cn/social?appType=2&app_version=5.0.4&client_secret" +
            "=c93f2c29298e27c3abbab38628fab244&client_type=android&method=app.social.getForumCarouselInfo&os_version" +
            "=android5.1%3AGoogle_Nexus_5_-_5.1.0_-_API_22_-_1080x1920&screen_size=1080x1776&v=7";
}
