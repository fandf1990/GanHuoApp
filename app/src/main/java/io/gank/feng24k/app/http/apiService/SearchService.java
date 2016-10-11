package io.gank.feng24k.app.http.apiService;

import java.util.List;

import io.gank.feng24k.app.model.entity.SearchResultInfo;
import io.gank.feng24k.app.model.entity.base.HttpResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
    @GET("api/search/query/listview/category/{category}/count/{pageSize}/page/{pageIndex}")
    public Observable<HttpResult<List<SearchResultInfo>>> searchByCategory(@Path("category") String category,
                                                                           @Path("pageSize") int pageSize,
                                                                           @Path("pageIndex") int pageIndex);

    @GET("search")
    public Call<String> searchByQuery(@Query("q") String query);
}
