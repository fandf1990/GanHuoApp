package io.gank.feng24k.app.http;


import rx.Subscriber;

public abstract class HttpSubscriber<T>  extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailer(e);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onFailer(Throwable e);
    public abstract void onSuccess(T t);
}
