package io.feng24k.app.gank.http.retrofit;

import io.feng24k.app.gank.http.OkHttpFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    String mBaseUrl = "http://gank.io/api/";

    private static RetrofitService mRetrofitService;
    private static Retrofit mRetrofit;


    public static RetrofitService getInstance() {
        if(mRetrofitService==null){
            mRetrofitService = new RetrofitService();
        }
        return mRetrofitService;
    }


    public <T> T create(Class<T> claz) {
        if(mRetrofit==null){
            mRetrofit = new Retrofit.Builder()
                    .client(OkHttpFactory.getInstance().getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(mBaseUrl)
                    .build();
        }
        return  mRetrofit.create(claz);
    }
}
