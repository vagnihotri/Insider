package com.insider.exercise.data.repository;

import com.insider.exercise.data.net.RestApi;
import com.insider.exercise.domain.entity.EventDataEntity;
import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.domain.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EventDataRepository extends RestApiRepository implements EventRepository {

    private final RestApi restApi;

    @Inject
    public EventDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<EventEntity>> getEvents() {
        return this.restApi.fetchEvents()
                .map(listResponse -> {
                    handleResponseError(listResponse);
                    EventDataEntity eventDataEntity = listResponse.body();
                    List<EventEntity> events = new ArrayList<>();
                    events.addAll(eventDataEntity.getList().getMasterList().values());
                    return events;
                });
    }
}
