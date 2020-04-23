package com.jersey.practicalmodel.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jersey.practicalmodel.mvp.BasePresenter;
import com.jersey.practicalmodel.mvp.BaseView;

public abstract class BaseActivity<P extends BasePresenter, V extends BaseView> extends Activity implements BaseView {

    private P iPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        iPresenter = getIPresenter();
        iPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (iPresenter != null) {
            iPresenter.dattchView();
        }
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract P getIPresenter();


    protected void setUpData() {

    }
}
