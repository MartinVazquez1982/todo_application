package org.example.storage;


import java.io.*;
import java.util.*;
import org.example.system.task.Task;

/**
 * Tha class CSVFileManager store and retrieve tasks of a CSV file
 */
public class CsvFileManager implements FileManager{

    private String fileName;

    /**
     * It creates a CSVFileManager
     *
     * @param fileName path of the CSV file
     */
    public CsvFileManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read all tasks in the CSV file
     *
     * @return Task list
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Store all task in the CSV file
     *
     * @param tasks Task list for store
     * @throws IOException if an I/O error occurs
     */
    public void writeAllTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter  bw = new BufferedWriter (new FileWriter(fileName))) {
            for (Task task : tasks) {
                bw.write(task.getTitle() + "," + task.getDescription() + "," + task.getState());
                bw.newLine();
            }
        }

    }
}
