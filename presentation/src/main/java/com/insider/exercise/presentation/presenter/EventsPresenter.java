package com.insider.exercise.presentation.presenter;

import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.domain.interactor.GetEventsUseCase;
import com.insider.exercise.presentation.view.BaseView;
import com.insider.exercise.presentation.view.EventsView;

import java.util.List;

import javax.inject.Inject;


public class EventsPresenter extends BasePresenter implements Presenter {

    private GetEventsUseCase getEventsUseCase;
    EventsView eventsView;

    @Inject
    public EventsPresenter(GetEventsUseCase getEventsUseCase) {
        super(getEventsUseCase);
        this.getEventsUseCase = getEventsUseCase;
    }

    @Override
    public void initWithView(BaseView view) {
        super.initWithView(view);
        this.eventsView = (EventsView) view;
    }

    @Override
    public void resume() {
        this.showLoader();
        this.getEventsUseCase.execute(new EventsSubscriber());
    }

    @Override
    public void destroy() {
        super.destroy();
        this.eventsView = null;
    }

    protected class EventsSubscriber extends BaseSubscriber<List<EventEntity>> {

        @Override public void onNext(List<EventEntity> events) {
            EventsPresenter.this.hideLoader();
            EventsPresenter.this.eventsView.showEvents(events);
        }
    }

}
