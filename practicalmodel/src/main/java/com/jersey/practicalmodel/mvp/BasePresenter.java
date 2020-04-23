package com.jersey.practicalmodel.mvp;


public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {

    protected M model;
    protected V view;


    public BasePresenter() {
        this.model = createModel();
    }

    protected abstract M createModel();

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void dattchView() {
        this.view = null;
    }


}
