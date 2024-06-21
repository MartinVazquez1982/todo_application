package org.example.storage;

import org.example.system.task.Task;

import java.io.IOException;
import java.util.List;

public interface FileManager {

    List<List<String>> read() throws IOException;
    void writeAllTasks(List<Task> tasks) throws IOException;
}
