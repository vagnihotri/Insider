package com.insider.exercise.data.net.interceptor;

import com.insider.exercise.data.net.RestApi;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HttpInterceptor implements Interceptor {

    @Inject
    public HttpInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Accept", RestApi.VERSION_HEADER)
                .build();
        return chain.proceed(request);
    }

}
