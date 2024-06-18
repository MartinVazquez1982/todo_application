package org.example.system;

import java.util.Objects;

public class Task {

    private static int taskCount = 0;
    private int id;
    private String title;
    private String description;
    private TaskState state;

    public Task(String title, String description) {
        this.id = ++taskCount;
        this.state = TaskState.TODO;
        this.title = title;
        this.description = description;
    }

    public Task(int id, String title, String description, TaskState state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
    }

    public Task (int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskState getState() {
        return state;
    }

    public void markInProgress() {
        this.state = TaskState.IN_PROGRESS;
    }

    public void markCompleted() {
        this.state = TaskState.DONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
