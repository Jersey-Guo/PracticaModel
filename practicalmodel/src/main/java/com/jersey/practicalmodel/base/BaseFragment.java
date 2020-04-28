package com.jersey.practicalmodel.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jersey.practicalmodel.mvp.BasePresenter;
import com.jersey.practicalmodel.mvp.BaseView;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected Activity mActivity;
    protected View mView;

    protected P iPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        return mView;
    }

    protected abstract P getPresenter();


    public abstract int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }else{
            this.mActivity = getActivity();
        }
    }

    public  <V extends View> V findViewById(@IdRes int res) {
        if (mView != null) {
            return mView.findViewById(res);
        }
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iPresenter = getPresenter();
        iPresenter.attachView(this);
        initView(savedInstanceState);
        setUpData();
    }

    @Override
    public void onDestroy() {
        if(iPresenter != null){
            iPresenter.dattchView();
        }
        super.onDestroy();
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected  void setUpData(){

    }
}
