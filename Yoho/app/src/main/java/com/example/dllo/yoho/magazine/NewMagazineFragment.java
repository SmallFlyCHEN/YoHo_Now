package com.example.dllo.yoho.magazine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/23.
 */
public class NewMagazineFragment extends BaseFragment{


    private ImageView boyIvOne, boyIvTwo, boyIvThree, grilIvOne, grilIvTwo, grilIvThree, specIvOne, specIvTwo, specIvThree;
    private TextView boyTvOne, boyTvTwo, boyTvThree, grilTvOne, grilTvTwo, grilTvThree, specTvOne, specTvTwo, specTvThree;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_newmagazine;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        boyIvOne = (ImageView) view.findViewById(R.id.new_magazine_iv_boy_one);
        boyIvTwo = (ImageView) view.findViewById(R.id.new_magazine_iv_boy_two);
        boyIvThree = (ImageView) view.findViewById(R.id.new_magazine_iv_boy_three);

        grilIvOne = (ImageView) view.findViewById(R.id.new_magazine_iv_gril_one);
        grilIvTwo = (ImageView) view.findViewById(R.id.new_magazine_iv_gril_two);
        grilIvThree = (ImageView) view.findViewById(R.id.new_magazine_iv_gril_three);

        specIvOne = (ImageView) view.findViewById(R.id.new_magazine_iv_spec_one);
        specIvTwo = (ImageView) view.findViewById(R.id.new_magazine_iv_spec_two);
        specIvThree = (ImageView) view.findViewById(R.id.new_magazine_iv_spec_three);

        boyTvOne = (TextView) view.findViewById(R.id.new_magazine_tv_boy_one);
        boyTvTwo = (TextView) view.findViewById(R.id.new_magazine_tv_boy_two);
        boyTvThree = (TextView) view.findViewById(R.id.new_magazine_tv_boy_three);

        grilTvOne = (TextView) view.findViewById(R.id.new_magazine_tv_gril_one);
        grilTvTwo = (TextView) view.findViewById(R.id.new_magazine_tv_gril_two);
        grilTvThree = (TextView) view.findViewById(R.id.new_magazine_tv_gril_three);

        specTvOne = (TextView) view.findViewById(R.id.new_magazine_tv_spec_one);
        specTvTwo = (TextView) view.findViewById(R.id.new_magazine_tv_spec_two);
        specTvThree = (TextView) view.findViewById(R.id.new_magazine_tv_spec_three);
    }

    //初始化数据
    @Override
    protected void initData() {

        getOneData();
        getTwoData();
        getThreeData();
    }

    private void getOneData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.NEWMAGAZINE_ONE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewMagazineOneBean oneBean = gson.fromJson(response, NewMagazineOneBean.class);
                Picasso.with(getActivity()).load(oneBean.getData().get(0).getCover()).into(boyIvOne);
                Picasso.with(getActivity()).load(oneBean.getData().get(1).getCover()).into(boyIvTwo);
                Picasso.with(getActivity()).load(oneBean.getData().get(2).getCover()).into(boyIvThree);

                boyTvOne.setText(oneBean.getData().get(0).getJournal());
                boyTvTwo.setText(oneBean.getData().get(1).getJournal());
                boyTvThree.setText(oneBean.getData().get(2).getJournal());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void getTwoData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.NEWMAGAZINE_TWO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewMagazineTwoBean twoBean = gson.fromJson(response, NewMagazineTwoBean.class);
                Picasso.with(getActivity()).load(twoBean.getData().get(0).getCover()).into(grilIvOne);
                Picasso.with(getActivity()).load(twoBean.getData().get(1).getCover()).into(grilIvTwo);
                Picasso.with(getActivity()).load(twoBean.getData().get(2).getCover()).into(grilIvThree);

                grilTvOne.setText(twoBean.getData().get(0).getJournal());
                grilTvTwo.setText(twoBean.getData().get(1).getJournal());
                grilTvThree.setText(twoBean.getData().get(2).getJournal());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void getThreeData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(URLValues.NEWMAGAZINE_THREE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewMagazineThreeBean threeBean = gson.fromJson(response, NewMagazineThreeBean.class);
                Picasso.with(getActivity()).load(threeBean.getData().get(0).getCover()).into(specIvOne);
                Picasso.with(getActivity()).load(threeBean.getData().get(1).getCover()).into(specIvTwo);
                Picasso.with(getActivity()).load(threeBean.getData().get(2).getCover()).into(specIvThree);

                specTvOne.setText(threeBean.getData().get(0).getJournal());
                specTvTwo.setText(threeBean.getData().get(1).getJournal());
                specTvThree.setText(threeBean.getData().get(2).getJournal());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
