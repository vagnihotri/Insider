package com.insider.exercise.presentation;

import com.insider.exercise.presentation.dependency.ActivityScope;
import com.insider.exercise.presentation.dependency.component.ApplicationComponent;
import com.insider.exercise.presentation.dependency.component.FragmentInjector;

import dagger.Component;

@ActivityScope
@Component(modules = TestMockerModule.class, dependencies = ApplicationComponent.class)
public interface TestMockerComponent extends FragmentInjector {}
