package com.jersey.practicamodel.ui.main;

import android.content.Context;
import android.view.View;

import com.jersey.practicalmodel.base.BaseViewHolder;
import com.jersey.practicamodel.R;
import com.jersey.practicamodel.login.LoginIPresenter;

public class MainViewHolder extends BaseViewHolder {

    private LoginIPresenter presenter;

    public MainViewHolder(Context context, View rootView) {
        super(context, rootView);
    }

    public void setPresenter(LoginIPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void setOnClick(View view, int viewId) {
        switch (viewId) {
            case R.id.get_data:
                presenter.login();
                break;
            case R.id.save_data:
                presenter.sendPhone();
                break;
        }
    }

    @Override
    protected void setUpView() {
        setOnClickListener(R.id.get_data);
        setOnClickListener(R.id.save_data);
    }
}
