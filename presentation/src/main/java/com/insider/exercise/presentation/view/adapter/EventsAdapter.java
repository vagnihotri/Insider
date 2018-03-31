package com.insider.exercise.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.insider.exercise.domain.entity.EventEntity;
import com.insider.exercise.presentation.R;

import java.util.Collections;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private Context context;
    private List<EventEntity> events;

    public EventsAdapter(Context context) {
        this.context = context;
        this.events = Collections.emptyList();
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
        this.notifyDataSetChanged();
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row_event, parent, false);
        EventsViewHolder vh = new EventsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        String eventName = events.get(position).getName();
        String eventImg = events.get(position).getHorizontal_cover_image();
        holder.nameTextView.setText(eventName);
        Glide.with(context)
                .load(eventImg)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView imageView;
        public View layout;

        public EventsViewHolder(View view) {
            super(view);
            layout = view;
            nameTextView = view.findViewById(R.id.eventName);
            imageView = view.findViewById(R.id.eventImg);
        }
    }
}
