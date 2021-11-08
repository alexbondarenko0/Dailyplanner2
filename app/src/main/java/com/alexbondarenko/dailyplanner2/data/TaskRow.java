package com.alexbondarenko.dailyplanner2.data;

public class TaskRow {
    private String upTime;
    private Task mTask;
    private String DownTime;

    public TaskRow(String upTime, Task task, String downTime) {
        this.upTime = upTime;
        mTask = task;
        DownTime = downTime;
    }

    public boolean hasTask() {
        return mTask != null;
    }

    public String getTaskName() {
        if (mTask == null)
            return "";
        else
            return mTask.getName();
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public Task getTask() {
        return mTask;
    }

    public void setTask(Task task) {
        mTask = task;
    }

    public String getDownTime() {
        return DownTime;
    }

    public void setDownTime(String downTime) {
        DownTime = downTime;
    }
}
