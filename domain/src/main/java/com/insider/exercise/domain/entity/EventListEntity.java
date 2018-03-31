package com.insider.exercise.domain.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by agni on 30/03/18.
 */

public class EventListEntity {

    private Map<String, EventEntity> masterList;

    public EventListEntity(Map<String, EventEntity> masterList) {
        this.masterList = masterList;
    }

    public Map<String, EventEntity> getMasterList() {
        return masterList;
    }

    public void setMasterList(Map<String, EventEntity> masterList) {
        this.masterList = masterList;
    }
}
