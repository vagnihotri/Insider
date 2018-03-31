package com.insider.exercise.presentation.view;

import com.insider.exercise.domain.entity.EventEntity;

import java.util.List;

/**
 * Created by agni on 30/03/18.
 */

public interface EventsView extends BaseView {

    void showEvents(List<EventEntity> events);
}
