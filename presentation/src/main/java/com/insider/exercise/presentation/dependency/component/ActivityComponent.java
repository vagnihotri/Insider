package com.insider.exercise.presentation.dependency.component;

import com.insider.exercise.presentation.dependency.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends FragmentInjector {}
