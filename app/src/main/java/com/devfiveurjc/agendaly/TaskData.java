package com.devfiveurjc.agendaly;

import java.util.ArrayList;

public class TaskData {
    static ArrayList<Task> tasks;
    static ArrayList<Task> tasksToday;
    static ArrayList<Task> tasksTmrw;
    static ArrayList<Task> tasksWeek;

    public static ArrayList<Task> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<Task>();
        }
        return tasks;
    }

    public static ArrayList<Task> getTasksToday() {
        if (tasksToday == null) {
            tasksToday = new ArrayList<Task>();
        }
        return tasksToday;
    }

    public static ArrayList<Task> getTasksTmrw() {
        if (tasksTmrw == null) {
            tasksTmrw = new ArrayList<Task>();
        }
        return tasksTmrw;
    }

    public static ArrayList<Task> getTasksWeek() {
        if (tasksWeek == null) {
            tasksWeek = new ArrayList<Task>();
        }
        return tasksWeek;
    }

}
