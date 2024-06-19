package org.example.view;

import org.example.system.Task;
import org.example.system.TaskAdministrator;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";

    private TaskAdministrator manager;

    public ConsoleView(TaskAdministrator manager) {
        this.manager = manager;
    }

    private void displayMenu() {
        System.out.println("\n--- Menú de To-Do List ---");
        System.out.println(ANSI_RED + "1. Crear tarea" + ANSI_RESET);
        System.out.println("2. Listar tareas");
        System.out.println("3. Actualizar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Marcar tarea como en proceso");
        System.out.println("6. Marcar tarea como completada");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }

    public void createTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el titulo: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el descripcion: ");
        String descripcion = scanner.nextLine();
        this.manager.addTask(new Task(title,descripcion));
    }

    public void listTasks(){
        List<Task> tasks = this.manager.getTasks();
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void deleteTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.removeTask(new Task(nro));
    }

    public void updateTask(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        System.out.print("Ingrese el nuevo titulo: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el nueva descripcion: ");
        String descripcion = scanner.nextLine();
        this.manager.editTask(nro, title, descripcion);
    }

    public void taskToInProgress(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.taskToInProgress(new Task(nro));
    }

    public void taskToInDone(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.taskToInDone(new Task(nro));
    }

    public void runApp(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        do {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.print(ANSI_CLS + ANSI_HOME);
            System.out.flush();
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    taskToInProgress();
                    break;
                case 6:
                    taskToInDone();
                    break;
                case 7:
                    running = false;
                    try{
                        this.manager.storeTasks();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Saliendo de la aplicación.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 7.");
            }
        } while (running);
    }
}
