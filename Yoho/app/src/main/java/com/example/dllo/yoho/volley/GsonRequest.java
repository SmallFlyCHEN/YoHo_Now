package com.example.dllo.yoho.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/11/29.
 */

public class GsonRequest<T> extends Request<T> {

    private Gson gson;
    private Response.Listener<T> listener;
    private Class<T> mClass;
    //用于post请求的map
    private HashMap<String, String> map;

    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> mClass) {
        super(method, url, listener);
        this.listener = listener1;
        this.mClass = mClass;
        gson = new Gson();
    }

    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> aClass, HashMap<String, String> map) {
        super(method, url, listener);
        gson = new Gson();
        this.listener = listener1;
        mClass = aClass;
        this.map = map;
    }

    //请求网络数据
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(data, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //这是网络请求失败的返回结果
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    //数据缓存
    @Override
    public void deliverError(VolleyError error) {
        if (error instanceof NoConnectionError){
            Cache.Entry entry = this.getCacheEntry();
            Response<T> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
            deliverResponse(response.result);
        }
        super.deliverError(error);
    }
}
