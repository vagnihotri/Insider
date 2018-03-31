package com.insider.exercise.presentation.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.presentation.R;
import com.insider.exercise.presentation.presenter.BasePresenter;
import com.insider.exercise.presentation.presenter.EventsPresenter;
import com.insider.exercise.presentation.view.EventsView;
import com.insider.exercise.presentation.view.adapter.EventsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by agni on 30/03/18.
 */

public class EventsFragment extends BaseFragment implements EventsView {

    @Inject
    EventsPresenter eventsPresenter;

    @BindView(R.id.events_recycler) RecyclerView recyclerView;

    @Override
    protected void callInjection() {
        this.getFragmentInjector().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_events;
    }

    @Override
    protected BasePresenter presenter() {
        return this.eventsPresenter;
    }

    @Override
    public void showEvents(List<EventEntity> events) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        EventsAdapter eventsAdapter = new EventsAdapter(getActivity());
        eventsAdapter.setEvents(events);
        recyclerView.setAdapter(eventsAdapter);
    }
}
