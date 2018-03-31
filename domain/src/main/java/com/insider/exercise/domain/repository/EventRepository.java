package com.insider.exercise.domain.repository;

import com.insider.exercise.domain.entity.EventEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by agni on 30/03/18.
 */

public interface EventRepository {
    Observable<List<EventEntity>> getEvents();
}
