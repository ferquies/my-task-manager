package com.ferquies.mytaskmanager.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ferquies.mytaskmanager.R;
import com.ferquies.mytaskmanager.domain.model.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by ferqu on 25/03/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.JobViewHolder> {

    private List<Task> tasksList;

    public TaskAdapter(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void addTask(Task task) {
        this.tasksList.add(task);
        notifyDataSetChanged();
    }

    public Task getLastTask() {
        Task result = null;

        if(tasksList != null && tasksList.size() > 0) {
            result = tasksList.get(tasksList.size() - 1);
        }

        return result;
    }

    public void stopTask() {
        if(tasksList != null && tasksList.size() > 0) {
            tasksList.get(tasksList.size() - 1).setEnd(new Date());
        }
        notifyDataSetChanged();
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_element, parent, false);
        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.textViewName.setText(tasksList.get(position).getName());
        holder.textViewDescription.setText(tasksList.get(position).getDescription());
        holder.textViewStart.setText(tasksList.get(position).getStart().toString());
        holder.textViewEnd.setText((tasksList.get(position).getEnd() != null ? tasksList.get(position).getEnd().toString() : ""));
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewStart;
        private TextView textViewEnd;

        public JobViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTaskName);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewTaskDescription);
            textViewStart = (TextView) itemView.findViewById(R.id.textViewTaskStart);
            textViewEnd = (TextView) itemView.findViewById(R.id.textViewTaskEnd);
        }

    }

}
