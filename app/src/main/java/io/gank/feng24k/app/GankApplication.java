package io.gank.feng24k.app;

import android.app.Application;

import com.jiongbull.jlog.JLog;

public class GankApplication extends Application{

    private static GankApplication mGankApplication;

    public static GankApplication getInstance(){
        return mGankApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGankApplication = this;
        JLog.init(this)
                .setDebug(BuildConfig.DEBUG);
    }
}
