package com.example.dllo.yoho.magazine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
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
        NetHelper.MyRequest(URLValues.NEWMAGAZINE_ONE_URL, NewMagazineOneBean.class, new NetListener<NewMagazineOneBean>() {
            @Override
            public void successListener(NewMagazineOneBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(boyIvOne);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(boyIvTwo);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(boyIvThree);

                boyTvOne.setText(response.getData().get(0).getJournal());
                boyTvTwo.setText(response.getData().get(1).getJournal());
                boyTvThree.setText(response.getData().get(2).getJournal());

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void getTwoData() {
        NetHelper.MyRequest(URLValues.NEWMAGAZINE_TWO_URL, NewMagazineTwoBean.class, new NetListener<NewMagazineTwoBean>() {
            @Override
            public void successListener(NewMagazineTwoBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(grilIvOne);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(grilIvTwo);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(grilIvThree);

                grilTvOne.setText(response.getData().get(0).getJournal());
                grilTvTwo.setText(response.getData().get(1).getJournal());
                grilTvThree.setText(response.getData().get(2).getJournal());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void getThreeData() {
        NetHelper.MyRequest(URLValues.NEWMAGAZINE_THREE_URL, NewMagazineThreeBean.class, new NetListener<NewMagazineThreeBean>() {
            @Override
            public void successListener(NewMagazineThreeBean response) {
                Picasso.with(getActivity()).load(response.getData().get(0).getCover()).into(specIvOne);
                Picasso.with(getActivity()).load(response.getData().get(1).getCover()).into(specIvTwo);
                Picasso.with(getActivity()).load(response.getData().get(2).getCover()).into(specIvThree);

                specTvOne.setText(response.getData().get(0).getJournal());
                specTvTwo.setText(response.getData().get(1).getJournal());
                specTvThree.setText(response.getData().get(2).getJournal());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
