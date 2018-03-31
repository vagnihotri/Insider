package com.insider.exercise.presentation.view.activity;

import android.os.Bundle;

import com.insider.exercise.presentation.R;
import com.insider.exercise.presentation.view.activity.base.CleanActivity;
import com.insider.exercise.presentation.view.fragment.EventsFragment;

public class MainActivity extends CleanActivity {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new EventsFragment());
        }
    }

    @Override
    protected boolean useBackToolbar() {
        return false;
    }
}
