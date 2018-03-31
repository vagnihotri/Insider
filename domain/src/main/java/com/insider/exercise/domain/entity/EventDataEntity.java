package com.insider.exercise.domain.entity;

public class EventDataEntity {

    public EventDataEntity(EventListEntity list) {
        this.list = list;
    }

    private EventListEntity list;

    public EventListEntity getList() {
        return list;
    }

    public void setList(EventListEntity list) {
        this.list = list;
    }
}
