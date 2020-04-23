package com.jersey.practicamodel.login;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jersey.practicalmodel.base.BaseCallback;
import com.jersey.practicalmodel.mvp.BasePresenter;
import com.jersey.practicamodel.App;


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
        model.login(name, pwd, new BaseCallback<LoginEntity>() {
            @Override
            public void onCallback(LoginEntity loginEntity) {
                if (loginEntity.isSuccess()) {
                    view.onSuccess(loginEntity);
                } else {
                    view.onFailed(loginEntity.code, loginEntity.errorMsg);
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

        model.sendCode(mobile, new BaseCallback<LoginEntity>() {
            @Override
            public void onCallback(LoginEntity loginEntity) {
                if (loginEntity.isSuccess()) {
                    view.onSuccess(loginEntity);
                } else {
                    view.onFailed(loginEntity.code, loginEntity.errorMsg);
                }
            }
        });
    }
}
