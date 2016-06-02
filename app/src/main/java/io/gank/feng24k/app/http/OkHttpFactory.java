package io.gank.feng24k.app.http;


import java.util.concurrent.TimeUnit;

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
            mOkHttpClient = okhttpBuilder.build();
        }
        return mOkHttpClient;
    }

}
