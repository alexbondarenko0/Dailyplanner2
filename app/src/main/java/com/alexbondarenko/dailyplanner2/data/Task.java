package com.alexbondarenko.dailyplanner2.data;

import java.io.Serializable;

public class Task implements Serializable {
    private long date_start;
    private long date_finish;
    private String name;
    private String description;

    public Task(long date_start, long date_finish, String name, String description) {
        this.date_start = date_start;
        this.date_finish = date_finish;
        this.name = name;
        this.description = description;
    }


    public long getDate_start() {
        return date_start;
    }

    public void setDate_start(long date_start) {
        this.date_start = date_start;
    }

    public long getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(long date_finish) {
        this.date_finish = date_finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Задача" +
                ", Дата начала=" + date_start +
                ", Дата конца=" + date_finish +
                ", Название='" + name + '\'' +
                ", Описание='" + description + '\'';
    }
}
