package io.gank.feng24k.app;

import android.app.Application;

import com.jiongbull.jlog.JLog;

public class GankApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        JLog.init(this)
                .setDebug(BuildConfig.DEBUG);
    }
}
