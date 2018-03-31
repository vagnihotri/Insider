package com.insider.exercise.presentation.dependency.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.insider.exercise.data.net.RestApi;
import com.insider.exercise.data.net.interceptor.HttpInterceptor;
import com.insider.exercise.data.repository.EventDataRepository;
import com.insider.exercise.domain.repository.EventRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    RestApi provideRestApi() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                                                .addInterceptor(new HttpInterceptor())
                                                .build();

        GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create());

        return new Retrofit.Builder()
                           .baseUrl(RestApi.URL_BASE)
                           .addConverterFactory(factory)
                           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                           .client(client)
                           .build()
                           .create(RestApi.class);
    }

    @Provides
    @Singleton
    EventRepository provideEventRepository(RestApi restApi) {
        return new EventDataRepository(restApi);
    }

}
