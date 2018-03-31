package com.insider.exercise.presentation.view.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.insider.exercise.data.net.error.RestApiErrorException;
import com.insider.exercise.presentation.BaseApplication;
import com.insider.exercise.presentation.R;
import com.insider.exercise.presentation.dependency.component.FragmentInjector;
import com.insider.exercise.presentation.view.BaseView;

public abstract class CleanActivity extends BaseActivity implements BaseView {

    private FragmentInjector fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }

    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((BaseApplication)getApplication()).getFragmentInjector();
        }
    }

    @Override
    public void handleError(Throwable error) {
        if (error instanceof RestApiErrorException) {
            showMessage(error.getMessage());
        }
        else Toast.makeText(context(), getResources().getString(R.string.message_error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

}
