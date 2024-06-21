package org.example.system.administrator;

import org.example.storage.FileManager;
import org.example.system.task.Task;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *  The TaskAdministrator does the administration of the tasks
 */
public abstract class TaskAdministrator {

    private final FileManager fileManager;

    /**
     * It creates a Task administrator array
     *
     * @param fileManager task storage
     */
    public TaskAdministrator(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Add a new task
     *
     * @param task new task
     */
    public abstract void addTask(Task task);

    /**
     * Remove a task
     *
     * @param task task to delete
     */
    public abstract void removeTask(Task task);

    /**
     * Returns a task
     *
     * @param task task to search
     * @return Optional with or without task
     */
    public abstract Optional<Task> getTask(Task task);

    /**
     * Returns all tasks
     *
     * @return List with the taks
     */
    public abstract List<Task> getTasks();

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
        this.fileManager.writeAllTasks(this.getTasks());
    }
}
