package org.example.system;

import org.example.storage.CsvFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskAdministrator {

    private ArrayList<Task> tasks = new ArrayList<>();
    private CsvFileManager fileManager;

    public TaskAdministrator(CsvFileManager fileManager) throws IOException {
        this.fileManager = fileManager;
        for (List<String> task : fileManager.read()) {
            this.tasks.add(new Task(Long.parseLong(task.get(0)), task.get(1), task.get(2), TaskState.valueOf(task.get(3))));
        }
    }

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

    public void storeTasks() throws IOException {
        this.fileManager.writeAllTasks(tasks);
    }

}
