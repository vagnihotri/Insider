package com.insider.exercise.presentation.dependency.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.insider.exercise.domain.executor.PostExecutionThread;
import com.insider.exercise.domain.executor.ThreadExecutor;
import com.insider.exercise.presentation.BaseApplication;
import com.insider.exercise.presentation.executor.JobExecutor;
import com.insider.exercise.presentation.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private static final String SHARED_PACKAGE = "base_shared_preferences";

    private final BaseApplication baseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.baseApplication;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PACKAGE, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
