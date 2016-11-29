package com.example.dllo.yoho.volley;

import com.android.volley.VolleyError;

/**
 * Created by dllo on 16/11/29.
 */

public interface NetListener<T> {
    //参数 就是解析后的数据
    void successListener(T response);
    //参数 对错误信息进行操作
    void errorListener(VolleyError error);
}
