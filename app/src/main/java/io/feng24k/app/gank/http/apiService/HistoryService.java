package io.feng24k.app.gank.http.apiService;

import java.util.List;

import io.feng24k.app.gank.model.entity.RecommendInfo;
import io.feng24k.app.gank.model.entity.base.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface HistoryService{
    /**
     * 获取发过干货日期接口
     * @return
     */
    @GET("day/history")
    public Observable<HttpResult<List<String>>> getDayHistory();

    /**
     * 获取某几日干货网站数据
     * @return
     */
    @GET("history/content/{pageSize}/{pageIndex}")
    public Observable<HttpResult<List<RecommendInfo>>> getHistoryContent(@Path("pageSize")int pageSize, @Path("pageIndex")int pageIndex);

    /**
     * 获取特定日期网站数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("history/content/day/{year}/{month}/{day}")
    public Observable<HttpResult<List<String>>> getHistoryContentDay(@Path("year")String year,@Path("month")String month,@Path("day")String day);
}
