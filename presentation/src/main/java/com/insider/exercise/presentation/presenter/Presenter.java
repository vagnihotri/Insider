package com.insider.exercise.presentation.presenter;

import com.insider.exercise.presentation.view.BaseView;

public interface Presenter {

    void initWithView(BaseView view);
    void resume();
    void pause();
    void destroy();

}
