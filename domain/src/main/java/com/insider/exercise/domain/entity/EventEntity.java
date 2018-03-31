package com.insider.exercise.domain.entity;


public class EventEntity {

    private String name;

    private String horizontal_cover_image;

    public EventEntity(String name, String horizontal_cover_image) {
        this.name = name;
        this.horizontal_cover_image = horizontal_cover_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHorizontal_cover_image() {
        return horizontal_cover_image;
    }

    public void setHorizontal_cover_image(String horizontal_cover_image) {
        this.horizontal_cover_image = horizontal_cover_image;
    }
}
