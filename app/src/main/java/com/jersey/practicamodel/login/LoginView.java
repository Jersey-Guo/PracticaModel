package com.jersey.practicamodel.login;

import com.jersey.practicalmodel.mvp.BaseView;

public interface LoginView extends BaseView {
    void onSuccess(LoginEntity entity);
    String getName();
    String getPwd();
    String getMobile();
}
