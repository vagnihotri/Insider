package com.insider.exercise.presentation.presenter;

import com.insider.exercise.data.net.error.RestApiErrorException;
import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.domain.interactor.GetEventsUseCase;
import com.insider.exercise.presentation.view.EventsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class EventsPresenterTest {

    @Mock GetEventsUseCase getEventsUseCase;
    @Mock EventsView mockEventsView;

    private EventsPresenter eventsPresenter;
    private EventsPresenter.EventsSubscriber eventsSubscriber;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.eventsPresenter = new EventsPresenter(this.getEventsUseCase);
        this.eventsPresenter.initWithView(this.mockEventsView);
        this.eventsSubscriber = this.eventsPresenter.new EventsSubscriber();
    }

    @Test
    public void testDestroy() {

        this.eventsPresenter.destroy();

        verify(this.getEventsUseCase).unsubscribe();
        assertNull(this.eventsPresenter.eventsView);
        assertNull(this.eventsPresenter.view);
    }

    @Test
    public void testGetEvents() throws Exception {

        this.eventsPresenter.resume();

        verify(this.mockEventsView).showLoader();
        verify(this.getEventsUseCase).execute(any(BasePresenter.BaseSubscriber.class));
    }

    @Test
    public void testSubscriberOnCompleted() {

        this.eventsSubscriber.onComplete();

        verify(this.mockEventsView).hideLoader();
    }

    @Test
    public void testSubscriberOnError() {

        this.eventsSubscriber.onError(new RestApiErrorException("Error message", 500));

        verify(this.mockEventsView).hideLoader();
        verify(this.mockEventsView).handleError(any(Throwable.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubscriberOnNext() {

        this.eventsSubscriber.onNext(new ArrayList<EventEntity>());

        verify(this.mockEventsView).hideLoader();
        verify(this.mockEventsView).showEvents(any(List.class));
    }
}
