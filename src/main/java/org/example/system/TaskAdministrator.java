package org.example.system;

import org.example.storage.CsvFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskAdministrator {

    private ArrayList<Task> tasks = new ArrayList<>();
    private CsvFileManager fileManager;
    private boolean loadedNoError = true;

    public TaskAdministrator(CsvFileManager fileManager) {
        this.fileManager = fileManager;
        try {
            for (List<String> task : fileManager.read()) {
                this.tasks.add(new Task(task.get(0), task.get(1), TaskState.valueOf(task.get(2))));
            }
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
            this.loadedNoError = false;
            System.out.println(e.getMessage());
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

    public Optional<Task> getTask(Task task) {
        Optional<Task> result = Optional.empty();
        if (task != null) {
            int i = tasks.indexOf(task);
            if (i >= 0) {
                result = Optional.of(tasks.get(i));
            }
            return result;
        }
        return result;
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
        Optional<Task> taskToEdit = getTask(task);
        taskToEdit.ifPresent(Task::markInProgress);
    }

    public void taskToInDone(Task task) {
        Optional<Task> taskToEdit = getTask(task);
        taskToEdit.ifPresent(Task::markCompleted);
    }

    public void storeTasks() throws IOException {
        this.fileManager.writeAllTasks(tasks);
    }

    public boolean wasLoadedNoError() {
        return loadedNoError;
    }

    public void setFileManager(CsvFileManager fileManager) {
        if (fileManager != null) {
            this.fileManager = fileManager;
        }
    }
}
