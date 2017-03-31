package io.feng24k.app.gank;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import io.feng24k.app.gank.http.OkHttpFactory;

public class GankApplication extends Application{

    private static GankApplication mGankApplication;

    public static GankApplication getInstance(){
        return mGankApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGankApplication = this;
        OkHttpUtils.initClient(OkHttpFactory.getInstance().getOkHttpClient());
    }
}
