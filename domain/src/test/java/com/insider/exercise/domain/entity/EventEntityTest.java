package com.insider.exercise.domain.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EventEntityTest {

    private static final String FAKE_EVENT_NAME = "Test Event";
    private static final String FAKE_EVENT_IMG = "http://event.insider.in";

    private EventEntity event;

    @Before
    public void setup() {
        this.event =  new EventEntity(FAKE_EVENT_NAME, FAKE_EVENT_IMG);
    }

    @Test
    public void testEventConstructor() {
        assertThat(this.event.getName(), is(FAKE_EVENT_NAME));
        assertThat(this.event.getHorizontal_cover_image(), is(FAKE_EVENT_IMG));
    }

    @Test
    public void testEventSetters() {
        this.event.setName("Another Test Event");
        this.event.setHorizontal_cover_image("http://anotherevent.insider.in");

        assertThat(this.event.getName(), is("Another Test Event"));
        assertThat(this.event.getHorizontal_cover_image(), is("http://anotherevent.insider.in"));
    }
}
