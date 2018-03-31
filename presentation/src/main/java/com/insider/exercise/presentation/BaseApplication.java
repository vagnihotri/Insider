package com.insider.exercise.presentation;

import android.app.Application;

import com.insider.exercise.presentation.dependency.component.ApplicationComponent;
import com.insider.exercise.presentation.dependency.component.DaggerActivityComponent;
import com.insider.exercise.presentation.dependency.component.DaggerApplicationComponent;
import com.insider.exercise.presentation.dependency.component.FragmentInjector;
import com.insider.exercise.presentation.dependency.module.ApplicationModule;

public class BaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public FragmentInjector getFragmentInjector() {
        return DaggerActivityComponent.builder()
                .applicationComponent(this.applicationComponent).build();
    }

}
