package com.alexbondarenko.dailyplanner2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexbondarenko.dailyplanner2.data.JSONHelper;
import com.alexbondarenko.dailyplanner2.data.Task;
import com.alexbondarenko.dailyplanner2.data.TaskRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    ArrayList<Task> allTasks;
    ArrayList<Task> tasksByDay;
    ArrayList<TaskRow> mTaskRows;

    TextView mDayStatus;
    CalendarView mCalendarView;
    RecyclerView mRecyclerView;
    Button mButtonAddTask;

    TaskRowAdapter mTaskRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDayStatus = findViewById(R.id.tvDayStatus);
        mRecyclerView = findViewById(R.id.recyclerView);
        mCalendarView = findViewById(R.id.calendarView);
        mButtonAddTask = findViewById(R.id.bAddTask);

        mDayStatus.setVisibility(View.INVISIBLE);

        if (JSONHelper.importFromJSON(getApplicationContext()) != null)
            allTasks = JSONHelper.importFromJSON(getApplicationContext());
        else
            allTasks = new ArrayList<>();


        mButtonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
            startActivity(intent);
        });

        mCalendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            if (JSONHelper.importFromJSON(getApplicationContext()) != null)
                allTasks = JSONHelper.importFromJSON(getApplicationContext());
            else
                allTasks = new ArrayList<>();

            mTaskRows = new ArrayList<>();

            tasksByDay = getTasksByDay(allTasks, dayOfMonth, month + 1, year);

            if (tasksByDay.size() == 0)
                mDayStatus.setText("No task for this day");
            else
                mDayStatus.setText(tasksByDay.size() + " tasks for this day");
            mDayStatus.setVisibility(View.VISIBLE);

            for (int i = 0; i < 24; i++) {
                TaskRow taskRow = new TaskRow(i + ":00", getTaskByHour(tasksByDay, i), (i + 1) + ":00");
                mTaskRows.add(taskRow);
            }

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mTaskRowAdapter = new TaskRowAdapter(mTaskRows, getApplicationContext());
            mRecyclerView.setAdapter(mTaskRowAdapter);

        });

    }

    private Task getTaskByHour(ArrayList<Task> taskList, int hour) {
        int h;
        for (Task task : taskList) {
            Date date = new Date(task.getDate_start());
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh");
            h = Integer.parseInt(formatForDateNow.format(date));
            if (h == hour)
                return task;
        }
        return null;
    }

    private ArrayList<Task> getTasksByDay(ArrayList<Task> taskList, int day, int month, int year) {
        ArrayList<Task> tasksByDay = new ArrayList<>();
        int d, m, y;
        for (Task task : taskList) {
            Date date = new Date(task.getDate_start());
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd/MM/yyyy");
            String[] str = formatForDateNow.format(date).split("/");
            d = Integer.parseInt(str[0]);
            m = Integer.parseInt(str[1]);
            y = Integer.parseInt(str[2]);

            if (d == day && m == month && y == year)
                tasksByDay.add(task);
        }
        return tasksByDay;
    }

}