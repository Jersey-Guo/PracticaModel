package com.jersey.practicamodel.login;

import android.text.TextUtils;

import com.jersey.practicalmodel.mvp.BasePresenter;
import com.jersey.practicalmodel.uis.ToastUtils;
import com.jersey.practicamodel.interfaces.JerseyCallback;


public class LoginIPresenter extends BasePresenter<LoginModel, LoginView> {

    @Override
    protected LoginModel createModel() {
        return new LoginModel();
    }

    public void login() {
        String name = view.getName();
        if (TextUtils.isEmpty(name)) {
            view.onFailed(0, "name is empty");
            return;
        }

        String pwd = view.getPwd();
        if (TextUtils.isEmpty(pwd)) {
            view.onFailed(0, "pwd is empty");
            return;
        }
        model.login(view.getAct(), name, pwd, new JerseyCallback<LoginEntity>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                ToastUtils.showLong(errorMsg);
            }

            @Override
            public void onNext(LoginEntity entity) {
                UserInfoBean infoBean = entity.getUser_info();
                if (infoBean != null) {
                    view.onSuccess(infoBean);
                    ToastUtils.showLong(infoBean.toString());
                }

            }
        });
    }


    public void sendPhone() {
        String mobile = view.getMobile();
        if (TextUtils.isEmpty(mobile)) {
            view.onFailed(0, "mobile is empty");
            return;
        }


    }
}
