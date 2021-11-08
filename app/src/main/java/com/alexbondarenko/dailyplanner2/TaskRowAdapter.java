package com.alexbondarenko.dailyplanner2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexbondarenko.dailyplanner2.data.TaskRow;

import java.util.List;

public class TaskRowAdapter extends RecyclerView.Adapter<TaskRowAdapter.TaskRowHolder> {
    public static Context mContext;
    private final List<TaskRow> mTaskRows;

    public TaskRowAdapter(List<TaskRow> taskRows, Context context) {
        mTaskRows = taskRows;
        mContext = context;
    }

    @NonNull
    @Override
    public TaskRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRowHolder holder, int position) {
        TaskRow taskRow = mTaskRows.get(position);
        holder.bind(taskRow);
    }

    @Override
    public int getItemCount() {
        return mTaskRows.size();
    }

    public static class TaskRowHolder extends RecyclerView.ViewHolder {
        private final TextView tvUpTime, tvName, tvDownTime;
        private TaskRow mTaskRow;

        public TaskRowHolder(@NonNull View itemView) {
            super(itemView);
            tvUpTime = itemView.findViewById(R.id.tvUpTime);
            tvName = itemView.findViewById(R.id.tvName);
            tvDownTime = itemView.findViewById(R.id.tvDownTime);

            itemView.setOnClickListener(v -> {
                if (mTaskRow.hasTask()) {
                    Intent intent = new Intent(mContext, TaskActivity.class);
                    intent.putExtra("ClickedTask", mTaskRow.getTask());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);

                }
            });
        }

        public void bind(TaskRow taskRow) {
            mTaskRow = taskRow;
            tvUpTime.setText(mTaskRow.getUpTime());
            tvName.setText(mTaskRow.getTaskName());
            tvDownTime.setText(mTaskRow.getDownTime());
        }
    }
}
