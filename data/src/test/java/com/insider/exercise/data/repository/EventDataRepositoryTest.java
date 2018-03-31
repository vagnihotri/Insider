package com.insider.exercise.data.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.insider.exercise.data.net.RestApi;
import com.insider.exercise.data.net.error.RestApiErrorException;
import com.insider.exercise.data.utils.TestUtils;
import com.insider.exercise.domain.entity.EventDataEntity;
import com.insider.exercise.domain.entity.EventEntity;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SuppressWarnings("unchecked")
public class EventDataRepositoryTest {

    private static final String EVENT_NAME = "fake_event_name";
    private static final String EVENT_IMG = "fake_event_name";

    private MockWebServer mockWebServer;
    private EventDataRepository eventDataRepository;
    private TestObserver testObserver;

    private EventEntity fakeEvent;

    @Before
    public void setUp() throws IOException {
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();

        this.eventDataRepository = new EventDataRepository(
                new Retrofit.Builder()
                        .baseUrl(mockWebServer.url("/"))
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                                .create()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(RestApi.class)
        );

        this.testObserver = new TestObserver();

        this.fakeEvent = new EventEntity(EVENT_NAME, EVENT_IMG);
    }

    @After
    public void tearDown() throws Exception {
        this.mockWebServer.shutdown();
    }

    @Test
    public void testFetchEventsSuccessResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(
                FileUtils.readFileToString(
                        TestUtils.getFileFromPath(this, "res/events_fetch_ok.json"))));

        this.eventDataRepository.getEvents().subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        List<EventEntity> responseNotes =
                (List<EventEntity>) ((List<Object>)testObserver.getEvents().get(0)).get(0);
        assertTrue(responseNotes.size() > 0);
        assertTrue(responseNotes.get(0).getName().length() > 0);
        assertTrue(responseNotes.get(0).getHorizontal_cover_image().length() > 0);
    }

    @Test
    public void testFetchEventsErrorResponse() throws Exception {
        this.mockWebServer.enqueue(new MockResponse().setResponseCode(401));

        this.eventDataRepository.getEvents().subscribe(this.testObserver);
        this.testObserver.awaitTerminalEvent();

        this.testObserver.assertValueCount(0);
        RestApiErrorException error = (RestApiErrorException) testObserver.errors().get(0);
        assertEquals(401, error.getStatusCode());
        assertTrue(error.getMessage().length() > 0);
    }

}
