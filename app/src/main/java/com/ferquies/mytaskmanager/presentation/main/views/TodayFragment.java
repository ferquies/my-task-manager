package com.ferquies.mytaskmanager.presentation.main.views;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.ferquies.mytaskmanager.R;
import com.ferquies.mytaskmanager.data.adapters.TaskAdapter;
import com.ferquies.mytaskmanager.domain.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodayFragment extends Fragment {

    private static final String FAB_STATUS_PLAY = "PLAY";
    private static final String FAB_STATUS_PAUSE = "PAUSE";

    private static TodayFragment instance;
    private List<Task> tasksList;
    private RecyclerView recyclerViewTask;
    private FloatingActionButton floatingActionButton;
    private View createTaskDialog;
    private View view;
    private EditText editTextTask;
    private EditText editTextDescription;
    private Button buttonCreateTask;
    private Button buttonCancel;
    private TaskAdapter taskAdapter = null;

    public TodayFragment() {}

    public static TodayFragment getInstance() {
        if(instance == null) {
            synchronized (TodayFragment.class) {
                if(instance == null) {
                    instance = new TodayFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, container, false);
        this.setUI();
        return view;
    }

    private void setUI() {
        this.tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasksList);
        recyclerViewTask = (RecyclerView) view.findViewById(R.id.recyclerViewTask);
        createTaskDialog = view.findViewById(R.id.create_task_dialog);
        editTextTask = (EditText) view.findViewById(R.id.editTextTask);
        editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        buttonCreateTask = (Button) view.findViewById(R.id.buttonCreateTask);
        buttonCancel = (Button) view.findViewById(R.id.buttonCancel);

        recyclerViewTask.setHasFixedSize(true);
        recyclerViewTask.setAdapter(taskAdapter);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        floatingActionButton.setTag(FAB_STATUS_PLAY);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(floatingActionButton.getTag().equals(FAB_STATUS_PLAY)) {
                    createTaskDialog.setVisibility(View.VISIBLE);
                    editTextTask.setText("");
                    editTextDescription.setText("");
                    createTaskDialog.animate();
                    createTaskDialog.requestFocus();
                    floatingActionButton.hide();
                } else {
                    taskAdapter.stopTask();
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_play));
                    floatingActionButton.setTag(FAB_STATUS_PLAY);
                }
            }
        });

        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Task task = new Task();
                task.setName(editTextTask.getText().toString());
                task.setDescription(editTextDescription.getText().toString());
                task.setStart(new Date());
                createTaskDialog.setVisibility(View.GONE);
                taskAdapter.addTask(task);
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(getContext(), android.R.drawable.ic_media_pause));
                floatingActionButton.setTag(FAB_STATUS_PAUSE);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                floatingActionButton.show();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTaskDialog.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                floatingActionButton.show();
            }
        });
    }

}
