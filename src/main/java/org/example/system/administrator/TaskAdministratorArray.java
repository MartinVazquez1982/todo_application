package org.example.system.administrator;

import org.example.storage.FileManager;
import org.example.system.task.Task;
import org.example.system.task.TaskState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  The TaskAdministratorArray does the administration of the tasks using ArrayList
 */
public class TaskAdministratorArray extends TaskAdministrator{

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * It creates a Task administrator array
     *
     * @param fileManager task storage
     */
    public TaskAdministratorArray(FileManager fileManager) {
        super(fileManager);
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public List<Task> getTasks() {
        return this.tasks.stream().toList();
    }
}
