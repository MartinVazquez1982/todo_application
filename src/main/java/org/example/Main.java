package org.example;


import org.example.storage.CsvFileManager;
import org.example.system.Task;
import org.example.system.TaskAdministrator;
import org.example.view.ConsoleView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        CsvFileManager storage = new CsvFileManager("src/main/resources/data.csv");
        TaskAdministrator manager = new TaskAdministrator(storage);
        ConsoleView console = new ConsoleView(manager);

        console.runApp();
    }
}