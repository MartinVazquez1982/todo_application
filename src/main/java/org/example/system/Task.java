package org.example.system;

import java.util.Objects;

public class Task {

    private static long taskCount = 0;
    private long id;
    private String title;
    private String description;
    private TaskState state;

    public Task(String title, String description) {
        this.id = ++taskCount;
        this.state = TaskState.TODO;
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, TaskState state) {
        this.id = ++taskCount;
        this.state = state;
        this.title = title;
        this.description = description;
    }

    public Task (long id){
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

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
