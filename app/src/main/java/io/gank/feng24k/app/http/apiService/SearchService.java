package io.gank.feng24k.app.http.apiService;

import io.gank.feng24k.app.model.entity.base.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author: fandf
 * Create Date: 2016/10/9.
 * Email: fandf@525happy.cn
 */

public interface SearchService {

    /**
     * 搜索 API
     * @return
     */
    @GET("search/query/listview/category/{category}/count/{pageSize}/page/{pageIndex}")
    public Observable<HttpResult<String>> searchByCategory(@Path("category") String category,
                                                                 @Path("pageSize") int pageSize,
                                                                 @Path("pageIndex") int pageIndex);
}
