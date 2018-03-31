package com.insider.exercise.presentation;

import com.insider.exercise.presentation.dependency.ActivityScope;
import com.insider.exercise.presentation.presenter.EventsPresenter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestMockerModule {

    @Provides @ActivityScope
    EventsPresenter provideNotesPresenter() {
        return Mockito.mock(EventsPresenter.class);
    }

}
