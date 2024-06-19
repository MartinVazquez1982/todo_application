package org.example.system;

import java.util.ArrayList;
import java.util.List;

public class TaskAdministrator {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        if (task != null) {
            tasks.remove(task);
        }
    }

    public List<Task> getTasks() {
        return this.tasks.stream().toList();
    }

    public void editTask(long id ,String newTitle, String newDescription) {
        Task taskToEdit = this.tasks.get(this.tasks.indexOf(new Task(id)));
        taskToEdit.setTitle(newTitle);
        taskToEdit.setDescription(newDescription);
    }

    public void taskToInProgress(Task task) {
        this.tasks.get(this.tasks.indexOf(task)).markInProgress();
    }

    public void taskToInDone(Task task) {
        this.tasks.get(this.tasks.indexOf(task)).markCompleted();
    }

}
