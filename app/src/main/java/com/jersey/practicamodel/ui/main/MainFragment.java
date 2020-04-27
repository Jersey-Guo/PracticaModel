package com.jersey.practicamodel.ui.main;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Toast;

import com.jersey.practicalmodel.base.BaseFragment;
import com.jersey.practicamodel.R;
import com.jersey.practicamodel.login.LoginEntity;
import com.jersey.practicamodel.login.LoginIPresenter;
import com.jersey.practicamodel.login.LoginView;

public class MainFragment extends BaseFragment<LoginIPresenter, LoginView> implements LoginView {


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

    @Override
    protected void initView(Bundle savedInstanceState) {
        MainViewHolder holder = new MainViewHolder(mActivity, mView);
        holder.setPresenter(iPresenter);
    }


    @Override
    public void onSuccess(LoginEntity o) {
        Toast.makeText(mActivity, o.getUser_info().getName(), Toast.LENGTH_LONG).show();
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
