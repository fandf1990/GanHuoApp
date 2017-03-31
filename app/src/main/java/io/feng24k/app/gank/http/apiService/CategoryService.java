package io.feng24k.app.gank.http.apiService;


import java.util.List;

import io.feng24k.app.gank.model.entity.BenefitEntity;
import io.feng24k.app.gank.model.entity.base.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CategoryService {


    @GET("data/{category}/{pageSize}/{pageIndex}")
    public Observable<HttpResult<List<BenefitEntity>>> getBenefitData(@Path("category")String category
            ,@Path("pageSize")int pageSize, @Path("pageIndex")int pageIndex);
}
