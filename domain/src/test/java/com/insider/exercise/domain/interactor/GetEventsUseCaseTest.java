package com.insider.exercise.domain.interactor;

import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.domain.executor.PostExecutionThread;
import com.insider.exercise.domain.executor.ThreadExecutor;
import com.insider.exercise.domain.repository.EventRepository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetEventsUseCaseTest {

    @Mock private ThreadExecutor mockThreadExecutor;
    @Mock private PostExecutionThread mockPostExecutionThread;
    @Mock private EventRepository mockEventRepository;

    @Before
    public void setup() { MockitoAnnotations.initMocks(this); }

    @Test
    public void testGetEventsUseCaseSuccess() {
        GetEventsUseCase getEventsUseCase = new GetEventsUseCase(mockThreadExecutor,
                mockPostExecutionThread, mockEventRepository);
        TestObserver<List<EventEntity>> testObserver = new TestObserver<>();
        List<EventEntity> events =
                Arrays.asList(new EventEntity("e1", "i1"),
                        new EventEntity("t2", "c2"));
        given(mockEventRepository.getEvents())
                .willReturn(Observable.just(events));

        getEventsUseCase.buildUseCaseObservable().subscribe(testObserver);

        Assert.assertEquals(events.size(),
                ((List<EventEntity>)(testObserver.getEvents().get(0)).get(0)).size());
        Assert.assertEquals(events.get(1).getName(),
                ((List<EventEntity>)(testObserver.getEvents().get(0)).get(0)).get(1).getName());
        verify(mockEventRepository).getEvents();
        verifyNoMoreInteractions(mockEventRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}
