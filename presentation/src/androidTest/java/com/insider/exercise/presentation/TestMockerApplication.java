package com.insider.exercise.presentation;

import com.insider.exercise.presentation.dependency.component.FragmentInjector;

public class TestMockerApplication extends BaseApplication {

    @Override
    public FragmentInjector getFragmentInjector() {
        return DaggerTestMockerComponent.builder()
                .applicationComponent(this.applicationComponent)
                .testMockerModule(new TestMockerModule()).build();
    }
}
