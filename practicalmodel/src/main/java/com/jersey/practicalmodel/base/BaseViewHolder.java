package com.jersey.practicalmodel.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;

/**
 * Create by Jersey On 2020/04/22
 * BaseViewHolder
 */
public abstract class BaseViewHolder {
    public Context context;
    private SparseArray<View> views;
    public View rootView;

    public BaseViewHolder(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        this.views = new SparseArray<>();
        setUpView();
    }

    public <V extends View> V findViewById(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    public void setOnClickListener(final int viewId) {
        final View view = findViewById(viewId);
        view.setOnClickListener(new BaseClickListener() {
            @Override
            public void onMultiClick(View v) {
                setOnClick(view, viewId);
            }
        });
    }

    protected abstract void setOnClick(View view, int viewId);

    protected abstract void setUpView();
}
