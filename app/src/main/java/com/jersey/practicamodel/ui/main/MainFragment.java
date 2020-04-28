package com.jersey.practicamodel.ui.main;


import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.jersey.practicalmodel.base.BaseFragment;
import com.jersey.practicamodel.R;
import com.jersey.practicamodel.login.LoginEntity;
import com.jersey.practicamodel.login.LoginIPresenter;
import com.jersey.practicamodel.login.LoginView;
import com.jersey.practicamodel.login.UserInfoBean;

public class MainFragment extends BaseFragment<LoginIPresenter> implements LoginView {


    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    protected LoginIPresenter getPresenter() {
        return new LoginIPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_fragment;
    }


    TextView messageTv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        MainViewHolder holder = new MainViewHolder(mActivity, mView);
        holder.setPresenter(iPresenter);
        messageTv = findViewById(R.id.message);
    }


    @Override
    public void onSuccess(UserInfoBean o) {
        messageTv.setText(String.format("%s\n%s\n%s\n%s\n%s", o.getName(), o.getAge(), o.getSex(), o.getEnName(), o.getCity()));
        Toast.makeText(mActivity, o.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(int code, String error) {
        Toast.makeText(mActivity, code + "--" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getName() {
        return "xiaoming";
    }

    @Override
    public String getPwd() {
        return "daming";
    }

    @Override
    public String getMobile() {
        return "1";
    }

    @Override
    public Activity getAct() {
        return mActivity;
    }
}
