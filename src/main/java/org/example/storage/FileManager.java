package org.example.storage;

import org.example.system.task.Task;

import java.io.IOException;
import java.util.List;

/**
 * Tha interface FileManager store and retrieve tasks
 */
public interface FileManager {

    /**
     * Read all tasks
     *
     * @return Task list
     * @throws IOException if an I/O error occurs
     */
    List<List<String>> read() throws IOException;

    /**
     * Store all task
     *
     * @param tasks Task list for store
     * @throws IOException if an I/O error occurs
     */
    void writeAllTasks(List<Task> tasks) throws IOException;
}
