package io.gank.feng24k.app.http.apiService;


import java.util.List;

import io.gank.feng24k.app.model.entity.BenefitEntity;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CategoryService {


    @GET("data/福利/{pageSize}/{pageIndex}")
    public Observable<HttpResult<List<BenefitEntity>>> getBenefitData(@Path("pageSize")int pageSize, @Path("pageIndex")int pageIndex);


}
