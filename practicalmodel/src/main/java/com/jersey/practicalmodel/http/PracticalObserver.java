package com.jersey.practicalmodel.http;

import android.app.Activity;
import android.app.Dialog;

import com.google.gson.JsonParseException;
import com.jersey.practicalmodel.constans.HttpConstants;
import com.jersey.practicalmodel.http.exception.ErrorCode;
import com.jersey.practicalmodel.http.interfaces.HttpCallback;
import com.jersey.practicalmodel.uis.dialogs.DialogUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public class PracticalObserver<T> extends DisposableObserver<T> {

    /**
     * 是否显示加载框
     */
    private boolean isShowDialog;
    private Activity mActivity;
    private Dialog dialog;
    private HttpCallback<T> callback;

    public PracticalObserver(Activity activity, Dialog dialog, boolean isShowDialog, HttpCallback<T> callback) {
        this.mActivity = activity;
        this.dialog = dialog;
        this.isShowDialog = isShowDialog;
        this.callback = callback;
        initDailog();
    }


    private void initDailog() {
        if (mActivity != null && isShowDialog && dialog == null) {
            DialogUtil mDialogUtil = new DialogUtil(mActivity);
            dialog = mDialogUtil.showLoading();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (isShowDialog && dialog != null
                && mActivity != null && !mActivity.isFinishing()) {
            dialog.show();
        }
        if (callback != null) {
            callback.onStart();
        }
    }


    @Override
    public void onNext(T t) {
        if (callback != null) {
            if (t instanceof ResponBody) {
                ResponBody responBody = (ResponBody) t;
                if (!responBody.isSuccess()) {
                    callback.onError(responBody.getCode(), responBody.getMsg());
                } else {
                    callback.onNext(t);
                }
            } else {
                callback.onNext(t);
            }

        }
    }

    @Override
    public void onError(Throwable e) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        onException(e);
    }

    private void onException(Throwable e) {
        String errorMsg;
        int errorCode = ErrorCode.UN_KNOW_ERROR;
        if (e instanceof HttpException) {
            errorCode = ErrorCode.NO_NET_MSG;
            errorMsg = (HttpConstants.NO_NET_MSG);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            errorCode = ErrorCode.NO_NET_MSG;
            errorMsg = (HttpConstants.NO_NET_MSG);
        } else if (e instanceof InterruptedIOException) {
            errorCode = ErrorCode.ERR_CONNECT_OUT_TIME_MSG;
            errorMsg = (HttpConstants.ERR_CONNECT_OUT_TIME_MSG);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            errorCode = ErrorCode.ERR_PARSE_MSG;
            errorMsg = (HttpConstants.ERR_PARSE_MSG);
            e.printStackTrace();
        } else {
            if (e != null) {
                errorMsg = (e.toString());
            } else {
                errorMsg = (HttpConstants.UN_KNOW_ERROR);
            }
        }
        if (callback != null) {
            callback.onError(errorCode, errorMsg);
        }
    }

    @Override
    public void onComplete() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (callback != null) {
            callback.onFinish();
        }
    }


}
