package io.gank.feng24k.app.http.apiService;

import java.util.List;

import io.gank.feng24k.app.model.entity.base.HttpResult;
import retrofit2.http.GET;
import rx.Observable;

public interface HistoryService{
    @GET("day/history")
    public Observable<HttpResult<List<String>>> getDayHistory();

}
