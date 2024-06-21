package org.example;


import org.example.storage.CsvFileManager;
import org.example.storage.FileManager;
import org.example.system.administrator.TaskAdministratorArray;
import org.example.view.View;
import org.example.view.viewTypes.ConsoleView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileManager storage = new CsvFileManager("src/main/resources/data.csv");
        TaskAdministratorArray manager = new TaskAdministratorArray(storage);
        View console = new ConsoleView(manager);

        console.runApp();
    }
}