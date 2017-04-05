package com.ferquies.mytaskmanager.presentation.main.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ferquies.mytaskmanager.R;

public class WeekFragment extends Fragment {

    private static WeekFragment instance;

    public WeekFragment() {}

    public static WeekFragment getInstance() {
        if(instance == null) {
            synchronized (WeekFragment.class) {
                if(instance == null) {
                    instance = new WeekFragment();
                }
            }
        }

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

}
