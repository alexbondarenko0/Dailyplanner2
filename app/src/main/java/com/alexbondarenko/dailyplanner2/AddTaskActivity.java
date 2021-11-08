package com.alexbondarenko.dailyplanner2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alexbondarenko.dailyplanner2.data.JSONHelper;
import com.alexbondarenko.dailyplanner2.data.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    TextView mErrors;
    EditText mName, mStartTime, mFinishTime, mDesc;
    Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mErrors = findViewById(R.id.a_TVErrors);
        mName = findViewById(R.id.a_edName);
        mStartTime = findViewById(R.id.a_edStartTime);
        mFinishTime = findViewById(R.id.a_edFinishTime);
        mDesc = findViewById(R.id.a_edDesc);
        mButtonAdd = findViewById(R.id.a_BntAddTask);

        mButtonAdd.setOnClickListener(v -> {
            String errList = "";
            if (validateText(mName.getText().toString())) {
                errList += "Incorrect name \n";
                mName.setText("");
            }
            if (validateText(mDesc.getText().toString())) {
                errList += "Incorrect description \n";
                mDesc.setText("");
            }
            if (validateDate(mStartTime.getText().toString())) {
                errList += "Incorrect start time \n";
                mStartTime.setText("");
            }
            if (validateDate(mFinishTime.getText().toString())) {
                errList += "Incorrect finish time \n";
                mFinishTime.setText("");
            }
            mErrors.setText(errList);
            if(errList.equals("")) {
                String name, desc;
                long startTime=0, finishTime=0;

                name = mName.getText().toString();
                desc = mDesc.getText().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm dd.MM.yyyy");
                try {
                    Date date = formatter.parse(mStartTime.getText().toString());
                    startTime = date.getTime();
                    date = formatter.parse(mFinishTime.getText().toString());
                    finishTime = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Task task = new Task(startTime, finishTime, name, desc);
                ArrayList<Task> allTasks;

                allTasks = JSONHelper.importFromJSON(getApplicationContext());
                allTasks.add(task);
                if (JSONHelper.exportToJSON(getApplicationContext(), allTasks))
                    Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "not added(((", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private boolean validateDate(String str){
        // Очень плохая валидация(

        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        try {
            format.parse(str);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    private boolean validateText(String str) {
        return str.length() == 0;
    }


}