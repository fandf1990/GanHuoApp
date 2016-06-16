package io.gank.feng24k.app.http;


import java.io.File;
import java.util.concurrent.TimeUnit;

import io.gank.feng24k.app.GankApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkHttpFactory {
    private static final int DEFAULT_TIMEOUT = 15;


    private static OkHttpFactory mOkHttpFactory;
    private static OkHttpClient mOkHttpClient;

    public static OkHttpFactory getInstance() {
        if (mOkHttpFactory == null) {
            mOkHttpFactory = new OkHttpFactory();
        }
        return mOkHttpFactory;
    }


    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
            okhttpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            String path = GankApplication.getInstance().getCacheDir().getPath()+ File.separator+"httpcache";
            File file = new File(path);
            okhttpBuilder.cache(new Cache(file,102400));
            mOkHttpClient = okhttpBuilder.build();
        }
        return mOkHttpClient;
    }

}
