package com.insider.exercise.domain.interactor;

import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.domain.executor.PostExecutionThread;
import com.insider.exercise.domain.executor.ThreadExecutor;
import com.insider.exercise.domain.repository.EventRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetEventsUseCase extends UseCase<List<EventEntity>> {

    private EventRepository eventRepository;

    @Inject
    public GetEventsUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                            EventRepository eventRepository) {
        super(threadExecutor, postExecutionThread);
        this.eventRepository = eventRepository;
    }

    @Override
    protected Observable<List<EventEntity>> buildUseCaseObservable() {
        return this.eventRepository.getEvents();
    }
}
