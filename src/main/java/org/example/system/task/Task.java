package org.example.system.task;

import java.util.Objects;

/**
 * The Task class represents a task with a name, a description and a state
 */
public class Task {

    private static long taskCount = 0;
    private long id;
    private String title;
    private String description;
    private TaskState state;

    /**
     * It creates a task with name and description. Put a new id and state in TODO
     *
     * @param title Task title
     * @param description Task description
     */
    public Task(String title, String description) {
        this(title, description, TaskState.TODO);
    }

    /**
     * It creates a task with name, description and state. Put a new id
     *
     * @param title Task title
     * @param description Task description
     */
    public Task(String title, String description, TaskState state) {
        this.id = ++taskCount;
        this.state = state;
        this.title = title;
        this.description = description;
    }

    /**
     * It creates a task with an id
     *
     * @param id Task id
     */
    public Task (long id){
        this.id = id;
    }

    /**
     * Returns the title of a task
     *
     * @return Task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of a task
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the state of a task
     *
     * @return Task state
     */
    public TaskState getState() {
        return state;
    }

    /**
     * Marks a task in progress
     */
    public void markInProgress() {
        this.state = TaskState.IN_PROGRESS;
    }

    /**
     * Marks a task in done
     */
    public void markCompleted() {
        this.state = TaskState.DONE;
    }

    /**
     * Changes task title
     *
     * @param title new title
     */
    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    /**
     * Changes task description
     *
     * @param description new description
     */
    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    /**
     * Returns the id of a task
     *
     * @return Task id
     */
    public long getId() {
        return id;
    }

    /**
     * Compares this task to the specified object to determine equality.
     * Two taks is equal if they have the same ID
     *
     * @param o The object to compare with this task
     * @return {@code true} if the object is equal to this task, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id;
    }

    /**
     * Returns a hash code for this task
     *
     * @return Hask code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Returns a represents in string format of this task
     *
     * @return A string that represents this task.
     */
    @Override
    public String toString() {
        return String.format(
                        "\n => Tarea <= \n" +
                        " => Id: %d,\n" +
                        " => Titulo: '%s',\n" +
                        " => Descripcion: '%s',\n" +
                        " => Estado: %s",
                id, title, description, state
        );
    }
}
