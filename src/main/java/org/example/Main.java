package org.example;


import org.example.system.TaskAdministrator;
import org.example.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        TaskAdministrator manager = new TaskAdministrator();

        ConsoleView console = new ConsoleView(manager);

        console.runApp();
    }
}