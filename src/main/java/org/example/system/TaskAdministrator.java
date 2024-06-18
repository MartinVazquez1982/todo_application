package org.example.system;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TaskAdministrator {

    private LinkedHashSet<Task> tasks = new LinkedHashSet<>();

    public TaskAdministrator() {
        super();
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
        return new ArrayList<>(tasks);
    }
}
