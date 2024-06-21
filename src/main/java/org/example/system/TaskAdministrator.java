package org.example.system;

import org.example.storage.CsvFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  The TaskAdministrator does the administration of the tasks
 */
public class TaskAdministrator {

    private ArrayList<Task> tasks = new ArrayList<>();
    private CsvFileManager fileManager;

    /**
     * It creates a Task administrator
     *
     * @param fileManager task storage
     */
    public TaskAdministrator(CsvFileManager fileManager) {
        this.fileManager = fileManager;
        try {
            for (List<String> task : fileManager.read()) {
                this.tasks.add(new Task(task.get(0), task.get(1), TaskState.valueOf(task.get(2))));
            }
        } catch (IOException e) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Add a new task
     *
     * @param task new task
     */
    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    /**
     * Remove a task
     *
     * @param task task to delete
     */
    public void removeTask(Task task) {
        if (task != null) {
            tasks.remove(task);
        }
    }

    /**
     * Returns a task
     *
     * @param task task to search
     * @return Optional with or without task
     */
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

    /**
     * Returns all tasks
     *
     * @return List with the taks
     */
    public List<Task> getTasks() {
        return this.tasks.stream().toList();
    }

    /**
     * Update a task
     *
     * @param id Task id
     * @param newTitle new title of this task
     * @param newDescription new description of this task
     */
    public void editTask(long id ,String newTitle, String newDescription) {
        Optional<Task> taskToEdit = this.getTask(new Task(id));
        if (taskToEdit.isPresent()) {
            taskToEdit.get().setTitle(newTitle);
            taskToEdit.get().setDescription(newDescription);
        }
    }

    /**
     * Marks a task in in progress
     *
     * @param task task to mark in progress
     */
    public void taskToInProgress(Task task) {
        Optional<Task> taskToEdit = getTask(task);
        taskToEdit.ifPresent(Task::markInProgress);
    }

    /**
     * Marks a task in done
     *
     * @param task task to mark in done
     */
    public void taskToInDone(Task task) {
        Optional<Task> taskToEdit = getTask(task);
        taskToEdit.ifPresent(Task::markCompleted);
    }

    /**
     * Stores all tasks
     *
     * @throws IOException if an I/O error occurs
     */
    public void storeTasks() throws IOException {
        this.fileManager.writeAllTasks(tasks);
    }
}
