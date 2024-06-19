package org.example.storage;


import java.io.*;
import java.util.*;
import org.example.system.Task;

public class CsvFileManager {

    private String fileName;

    public CsvFileManager(String fileName) {
        this.fileName = fileName;
    }

    public List<List<String>> read() throws IOException {
        List<List<String>> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                list.add(Arrays.asList(values));
            }
        }
        return list;
    }

    public void writeAllTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter  bw = new BufferedWriter (new FileWriter(fileName))) {
            for (Task task : tasks) {
                bw.write(task.getTitle() + "," + task.getDescription() + "," + task.getState());
                bw.newLine();
            }
        }

    }
}
