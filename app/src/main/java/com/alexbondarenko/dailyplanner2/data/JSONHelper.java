package com.alexbondarenko.dailyplanner2.data;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONHelper {
    private static final String FILE_NAME = "data.json";

    public static boolean exportToJSON(Context context, ArrayList<Task> dataList) {
        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setTasks(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Task> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)) {

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(inputStreamReader, DataItems.class);
            return dataItems.getTasks();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItems {
        private ArrayList<Task> tasks;

        public ArrayList<Task> getTasks() {
            return tasks;
        }

        public void setTasks(ArrayList<Task> tasks) {
            this.tasks = tasks;
        }
    }
}

