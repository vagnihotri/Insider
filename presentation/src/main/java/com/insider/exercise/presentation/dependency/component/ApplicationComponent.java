package com.insider.exercise.presentation.dependency.component;

import android.content.Context;

import com.insider.exercise.data.net.RestApi;
import com.insider.exercise.domain.executor.PostExecutionThread;
import com.insider.exercise.domain.executor.ThreadExecutor;
import com.insider.exercise.domain.repository.EventRepository;
import com.insider.exercise.presentation.dependency.module.ApplicationModule;
import com.insider.exercise.presentation.dependency.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    RestApi restApi();
    EventRepository eventRepository();

}
