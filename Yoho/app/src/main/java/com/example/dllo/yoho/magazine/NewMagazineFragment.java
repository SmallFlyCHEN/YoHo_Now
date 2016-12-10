package com.example.dllo.yoho.magazine;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.dllo.yoho.GreenDao.CollectBean;
import com.example.dllo.yoho.GreenDao.DBTools;
import com.example.dllo.yoho.R;
import com.example.dllo.yoho.URLValues;
import com.example.dllo.yoho.base.BaseFragment;
import com.example.dllo.yoho.volley.NetHelper;
import com.example.dllo.yoho.volley.NetListener;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/23.
 */
public class NewMagazineFragment extends BaseFragment implements View.OnClickListener {


    private ImageView boyIvOne, boyIvTwo, boyIvThree, grilIvOne, grilIvTwo, grilIvThree, specIvOne, specIvTwo, specIvThree;
    private TextView boyTvOne, boyTvTwo, boyTvThree, grilTvOne, grilTvTwo, grilTvThree, specTvOne, specTvTwo, specTvThree;
    private NewMagazineOneBean oneBean;
    private NewMagazineTwoBean twoBean;
    private NewMagazineThreeBean threeBean;
    private String urlImger;
    private String text;
    private PopupWindow popupWindow;

    //绑定布局
    @Override
    protected int setLayout() {
        return R.layout.fragment_newmagazine;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        oneBean = new NewMagazineOneBean();
        twoBean = new NewMagazineTwoBean();
        threeBean = new NewMagazineThreeBean();
        //ID和点击事件
        seekID(view);
    }

    private void seekID(View view) {
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

        boyIvOne.setOnClickListener(this);
        boyIvTwo.setOnClickListener(this);
        boyIvThree.setOnClickListener(this);
        grilIvOne.setOnClickListener(this);
        grilIvTwo.setOnClickListener(this);
        grilIvThree.setOnClickListener(this);
        specIvOne.setOnClickListener(this);
        specIvTwo.setOnClickListener(this);
        boyTvThree.setOnClickListener(this);
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
                oneBean = response;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_magazine_iv_boy_one:
                urlImger = oneBean.getData().get(0).getCover();
                text = oneBean.getData().get(0).getJournal();
                popIv();
                break;
            case R.id.new_magazine_iv_boy_two:
                break;
            case R.id.new_magazine_iv_boy_three:
                break;
            case R.id.new_magazine_iv_gril_one:
                break;
            case R.id.new_magazine_iv_gril_two:
                break;
            case R.id.new_magazine_iv_gril_three:
                break;
            case R.id.new_magazine_iv_spec_one:
                break;
            case R.id.new_magazine_iv_spec_two:
                break;
            case R.id.new_magazine_iv_spec_three:
                break;
            case R.id.item_pop_iv:
                popupWindow.dismiss();
                break;
            case R.id.item_pop_iv_del:
                popupWindow.dismiss();
                break;
            case R.id.item_pop_btn:
                CollectBean collectBean = new CollectBean();
                collectBean.setBoty(text);
                collectBean.setImg(urlImger);
                DBTools.getCollectBeanDao().insertCollect(collectBean);
                break;
        }
    }

    private void popIv() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.item_iv_pop, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);
        ImageView popIv = (ImageView) view.findViewById(R.id.item_pop_iv);
        ImageView delIv = (ImageView) view.findViewById(R.id.item_pop_iv_del);
        TextView tv = (TextView) view.findViewById(R.id.item_pop_tv_journal);
        Button btn = (Button) view.findViewById(R.id.item_pop_btn);
        Picasso.with(context).load(urlImger).into(popIv);
        tv.setText(text);
        popIv.setOnClickListener(this);
        delIv.setOnClickListener(this);
        btn.setOnClickListener(this);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view);
    }
}
