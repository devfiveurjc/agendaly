package com.devfiveurjc.agendaly.crud;

import com.devfiveurjc.agendaly.model.Task;

import java.util.List;

import io.realm.Realm;

public class CRUDTask {

    static Realm realm = Realm.getDefaultInstance();

    private static int calculateIndex() {
        Number currentId = realm.where(Task.class).max("id");
        int nextId = 0;
        if (currentId != null) {
            nextId = currentId.intValue() + 1;
        }
        return nextId;
    }

    public static void addTask (final Task task) {
        realm.executeTransaction(realm -> {
            int index = CRUDTask.calculateIndex();
            Task realmTask = realm.createObject(Task.class, index);
            setRealmTask(realmTask, task);
        });
    }

    public static void updateTask(int id, final Task task) {
        realm.beginTransaction();
        Task realmTask = getTask(id);
        setRealmTask(realmTask, task);
        realm.insertOrUpdate(realmTask);
        realm.commitTransaction();
    }

    private static void setRealmTask(Task realmTask, Task task) {
        realmTask.setTitle(task.getTitle());
        realmTask.setDescription(task.getDescription());
        realmTask.setDate(task.getDate());
        realmTask.setChecked(task.isChecked());
    }

    public static void deleteAllTasks() {
        realm.beginTransaction();
        List<Task> realmTasks = getAllTasks();
        for (Task realmTask : realmTasks) {
            realmTask.deleteFromRealm();
        }
        realm.commitTransaction();
    }

    public static void deleteTask(int id) {
        realm.beginTransaction();
        Task realmTask = getTask(id);
        realmTask.deleteFromRealm();
        realm.commitTransaction();
    }

    public static List<Task> getAllTasks() {
        return realm.where(Task.class).findAll();
    }

    public static Task getTask(int id) {
        return realm.where(Task.class).equalTo("id", id).findFirst();
    }

}
