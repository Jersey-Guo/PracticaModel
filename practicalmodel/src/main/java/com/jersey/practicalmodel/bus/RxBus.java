package com.jersey.practicalmodel.bus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private Subject<Object> mSubject;
    private Map<String, Disposable> mDisposableMap;

    private final Map<Class<?>, Object> mStickyEventMap;

    private static class InstanceHolder {
        private static RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private RxBus() {
        mSubject = PublishSubject.create().toSerialized();
        mStickyEventMap = new ConcurrentHashMap<>();
    }

    /**
     * 订阅
     *
     * @param tag        标签
     * @param disposable 订阅
     */
    public void regist(Object tag, Disposable disposable) {
        if (mDisposableMap == null) {
            mDisposableMap = new HashMap<>();
        }
        String key = tag.getClass().getName();
        Disposable dis = mDisposableMap.get(key);
        if (dis == null || dis.isDisposed()) {
            mDisposableMap.put(key, disposable);
        }
    }

    /**
     * 取消订阅
     *
     * @param tag 标签
     */
    public void unRegist(Object tag) {
        if (mDisposableMap == null) {
            return;
        }
        String key = tag.getClass().getName();
        if (!mDisposableMap.containsKey(key)) {
            return;
        }
        Disposable dis = mDisposableMap.get(key);
        if (dis != null && !dis.isDisposed()) {
            dis.dispose();
        }
        mDisposableMap.remove(key);
    }

    public void clearDisposable() {
        if (mDisposableMap != null) {
            mDisposableMap.clear();
            mDisposableMap = null;
        }
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mSubject.hasObservers();
    }

    /**
     * 发送消息
     *
     * @param o 发送对象
     */
    public void post(Object o) {
        mSubject.onNext(o);
    }

    /**
     * 返回结果
     */
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mSubject.ofType(tClass);
    }

    /**
     * 返回结果
     */
    public <T> Disposable doSubscribe(Class<T> eventType, final RxCallback<T> callback) {
        return toObservable(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (callback != null) {
                            callback.onNext(t);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(throwable);
                        }
                    }
                });
    }


    /**
     * Stciky 相关
     */

    /**
     * 发送一个新Sticky事件
     */
    public void postSticky(Object event) {
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        post(event);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
        synchronized (mStickyEventMap) {
            Observable<T> observable = mSubject.ofType(eventType);
            final Object event = mStickyEventMap.get(eventType);

            if (event != null) {
                return Observable.merge(observable, Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        emitter.onNext(eventType.cast(event));
                    }
                }));
            } else {
                return observable;
            }
        }
    }

    /**
     * 返回结果
     */
    public <T> Disposable doSubscribeSticky(Class<T> eventType, final RxCallback<T> callback) {
        return toObservableSticky(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (callback != null) {
                            callback.onNext(t);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(throwable);
                        }
                    }
                });
    }

    /**
     * 根据eventType获取Sticky事件
     */
    public <T> T getStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }

    /**
     * 移除指定eventType的Sticky事件
     */
    public <T> T removeStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    /**
     * 移除所有的Sticky事件
     */
    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }

}
