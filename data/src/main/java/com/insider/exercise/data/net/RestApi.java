package com.insider.exercise.data.net;

import com.insider.exercise.domain.entity.EventDataEntity;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface RestApi {

    String URL_BASE = "https://api.insider.in";
    String VERSION_HEADER =  "1";

    @GET("/home?norm=1&filterBy=go-out&city=mumbai")
    Observable<Response<EventDataEntity>> fetchEvents();
}
