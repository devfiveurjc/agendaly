package com.devfiveurjc.agendaly;

import java.util.ArrayList;

public class TaskData {
    static ArrayList<Task> tasks;


    public static ArrayList<Task> getTasks() {
        if (tasks==null){
            tasks = new ArrayList<Task>();
        }
        return tasks;
    }

}
