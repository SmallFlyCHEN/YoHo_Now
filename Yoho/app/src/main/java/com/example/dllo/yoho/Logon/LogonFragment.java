package com.example.dllo.yoho.Logon;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.yoho.R;
import com.example.dllo.yoho.base.BaseFragment;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.wevey.selector.dialog.NormalAlertDialog;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/6.
 */
public class LogonFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    private ImageView failure;
    private TextView yoHo;
    private EditText oneEdt;
    private ImageView oneSmall;
    private EditText twoEdt;
    private ImageView password;
    private ImageView twoSmall;
    private Button btn;
    private TextView forget;
    private ImageView siNa;
    private ImageView weiBo;
    private ImageView more;
    private ImageView icon;
    private ImageView up;
    private TextView back;

    private boolean hide = true;
    private NormalAlertDialog normalAlertDialog;
    private MDSelectionDialog dialog;
    private RelativeLayout rv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_logon;
    }

    @Override
    protected void initView(View view) {
        //找到ID对应的组件和点击事件
        seekID(view);
        onFocus();
    }

    //找到ID对应的组件和点击事件
    private void seekID(View view) {
        rv = (RelativeLayout) view.findViewById(R.id.logon_rl);
        oneEdt = (EditText) view.findViewById(R.id.logon_edt_one);
        twoEdt = (EditText) view.findViewById(R.id.logon_edt_two);
        failure = (ImageView) view.findViewById(R.id.logon_iv_failure);
        yoHo = (TextView) view.findViewById(R.id.logon_tv_yoho);
        oneSmall = (ImageView) view.findViewById(R.id.logon_iv_small_one);
        twoSmall = (ImageView) view.findViewById(R.id.logon_iv_small_two);
        password = (ImageView) view.findViewById(R.id.logon_iv_password);
        btn = (Button) view.findViewById(R.id.logon_btn);
        forget = (TextView) view.findViewById(R.id.logon_tv_forget);
        siNa = (ImageView) view.findViewById(R.id.logon_iv_sina);
        weiBo = (ImageView) view.findViewById(R.id.logon_iv_weibo);
        more = (ImageView) view.findViewById(R.id.logon_iv_more);
        icon = (ImageView) view.findViewById(R.id.logon_iv_icon);
        up = (ImageView) view.findViewById(R.id.logon_iv_up);
        back = (TextView) view.findViewById(R.id.logon_tv_back);

        failure.setOnClickListener(this);
        yoHo.setOnClickListener(this);
        oneSmall.setOnClickListener(this);
        twoSmall.setOnClickListener(this);
        password.setOnClickListener(this);
        btn.setOnClickListener(this);
        forget.setOnClickListener(this);
        siNa.setOnClickListener(this);
        weiBo.setOnClickListener(this);
        more.setOnClickListener(this);
        icon.setOnClickListener(this);
        up.setOnClickListener(this);
        back.setOnClickListener(this);

        //EditText监听事件
        oneEdt.addTextChangedListener(this);
        twoEdt.addTextChangedListener(this);
    }

    //EditText获取焦点
    private void onFocus() {
        oneEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //得到焦点时要做的操作
                    oneSmall.setVisibility(View.VISIBLE);
                } else {
                    //失去焦点时要做的操作
                    oneSmall.setVisibility(View.GONE);
                }
            }
        });

        twoEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //得到焦点时要做的操作
                    twoSmall.setVisibility(View.VISIBLE);
                } else {
                    //失去焦点时要做的操作
                    twoSmall.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logon_iv_failure:
                getActivity().finish();
                break;
            case R.id.logon_tv_yoho:
                DialogOne();
                break;
            case R.id.logon_iv_small_one:
                oneEdt.setText("");
                break;
            case R.id.logon_iv_small_two:
                twoEdt.setText("");
                break;
            case R.id.logon_iv_password:
                hide = !hide;
                //替换图片
                if (hide) {
                    password.setImageResource(R.mipmap.login_password_see_icon);
                    twoEdt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //设置光标的位置
                    twoEdt.setSelection(twoEdt.length());
                } else {
                    password.setImageResource(R.mipmap.login_password_unsee_icon);
                    twoEdt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    twoEdt.setSelection(twoEdt.length());
                }
                break;
            case R.id.logon_tv_forget:
                DialogTwo();
                break;
            case R.id.logon_btn:
                break;
            case R.id.logon_iv_sina:
                break;
            case R.id.logon_iv_weibo:
                break;
            case R.id.logon_iv_more:
                rv.setVisibility(View.VISIBLE);
                break;
            case R.id.logon_iv_icon:
                break;
            case R.id.logon_iv_up:
                rv.setVisibility(View.GONE);
                break;
            case R.id.logon_tv_back:
                //当CurrentItem代表的是Fragment就会显示下标为1的Fragment
                LogonActivity.vp.setCurrentItem(1);
                break;
        }
    }

    private void DialogOne() {
        NormalAlertDialog.Builder builder = new NormalAlertDialog.Builder(context)
                //屏幕高度
                .setHeight(0.23f)
                //屏幕宽度
                .setWidth(0.82f)
                //设置标题可见
                .setTitleVisible(true)
                //设置标题
                .setTitleText("YoHo!Family")
                //设置中间内容
                .setContentText("Yoho!Family账号登录YohoBuy!、Yoho!Now及SHOW")
                .setContentTextSize(15)
                //设置单一模式
                .setSingleMode(true)
                .setSingleButtonText("确定")
                .setContentTextColor(R.color.cancle)
                .setCanceledOnTouchOutside(true)
                .setSingleListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        normalAlertDialog.dismiss();
                    }
                });
        normalAlertDialog = new NormalAlertDialog(builder);
        normalAlertDialog.show();
    }

    private void DialogTwo() {
        //屏幕宽度*0.8
        dialog = new MDSelectionDialog.Builder(context)
                .setCanceledOnTouchOutside(true)
                .setItemHeight(50)
                .setItemWidth(0.8f)  //屏幕宽度*0.8
                .setItemTextSize(15)
                .setCanceledOnTouchOutside(true)
                .setOnItemListener(new DialogOnItemClickListener() {
                    @Override
                    public void onItemClick(Button button, int position) {
                        dialog.dismiss();
                    }
                })
                .build();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("忘记密码");
        arrayList.add("通过手机找回密码");
        arrayList.add("通过邮箱找回密码");
        arrayList.add("取消");
        dialog.setDataList(arrayList);
        dialog.show();
    }

    //文本改变之前的点击事件
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //文本正在改变的点击事件
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String one = oneEdt.getText().toString();
        String two = twoEdt.getText().toString();
        if (!one.isEmpty() && !two.isEmpty()) {
            btn.setBackgroundColor(Color.GREEN);
        } else {
            btn.setBackgroundColor(Color.GRAY);
        }
    }

    //文本改变了之后的点击事件
    @Override
    public void afterTextChanged(Editable s) {

    }
}
