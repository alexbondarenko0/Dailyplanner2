package com.alexbondarenko.dailyplanner2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alexbondarenko.dailyplanner2.data.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {

    TextView mTVName, mTVStartTime, mTVFinishTime, mTVDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mTVName = findViewById(R.id.t_tvName);
        mTVStartTime = findViewById(R.id.t_tvStartTime);
        mTVFinishTime = findViewById(R.id.t_tvFinishTime);
        mTVDesc = findViewById(R.id.t_tvDecs);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("ClickedTask");

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        mTVName.setText(task.getName());
        mTVStartTime.setText(formatForDateNow.format(new Date(task.getDate_start())));
        mTVFinishTime.setText(formatForDateNow.format(new Date(task.getDate_finish())));
        mTVDesc.setText(task.getDescription());

    }
}