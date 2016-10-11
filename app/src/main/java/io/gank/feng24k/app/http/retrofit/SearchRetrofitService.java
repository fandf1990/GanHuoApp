package io.gank.feng24k.app.http.retrofit;

import io.gank.feng24k.app.http.OkHttpFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchRetrofitService {

    String mBaseUrl = "http://gank.io/";

    private static SearchRetrofitService mRetrofitService;
    private static Retrofit mRetrofit;


    public static SearchRetrofitService getInstance() {
        if(mRetrofitService==null){
            mRetrofitService = new SearchRetrofitService();
        }
        return mRetrofitService;
    }


    public <T> T create(Class<T> claz) {
        if(mRetrofit==null){
            mRetrofit = new Retrofit.Builder()
                    .client(OkHttpFactory.getInstance().getOkHttpClient())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(mBaseUrl)
                    .build();
        }
        return  mRetrofit.create(claz);
    }
}
