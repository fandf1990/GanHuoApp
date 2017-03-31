package io.feng24k.app.gank.http;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.feng24k.app.gank.GankApplication;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpFactory {


    public static final String TAG="OkHttpFactory";

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

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okhttpBuilder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            okhttpBuilder.addInterceptor(interceptor);

            okhttpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okhttpBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            String path = GankApplication.getInstance().getCacheDir().getPath()+ File.separator+"httpcache";
            File file = new File(path);
            okhttpBuilder.cache(new Cache(file,10485760));
            mOkHttpClient = okhttpBuilder.build();
        }
        return mOkHttpClient;
    }
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor(){
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!NetUtils.hasNetwork(GankApplication.getInstance())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if(NetUtils.hasNetwork(GankApplication.getInstance())){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else{
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
