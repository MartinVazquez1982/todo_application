package org.example.view;

import org.example.system.Task;
import org.example.system.TaskAdministrator;
import org.example.system.TaskState;
import org.example.view.cleanConsole.CleanConsole;
import org.example.view.cleanConsole.LinuxCleanner;
import org.example.view.cleanConsole.WindowsCleanner;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private TaskAdministrator manager;
    private CleanConsole cleanner;

    public ConsoleView(TaskAdministrator manager) {
        this.manager = manager;
        if (System.getProperty("os.name").contains("Windows")) {
            this.cleanner = new WindowsCleanner();
        } else {
            this.cleanner = new LinuxCleanner();
        }
    }

    private void printTitle(String title) {
        System.out.println("\n" + ANSI_BLUE + title + ANSI_RESET);
    }

    private void displayMenu() {
        printTitle("--- Menú de To-Do List ---");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Actualizar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Marcar tarea como en proceso");
        System.out.println("6. Marcar tarea como completada");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }

    private void createTask(){
        printTitle("Crear tarea");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el titulo: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el descripcion: ");
        String descripcion = scanner.nextLine();
        this.manager.addTask(new Task(title,descripcion));
    }

    private void listTasks(){
        List<Task> tasks = this.manager.getTasks();
        printTitle("=== Lista de Tareas ===");
        StringBuilder toDo = new StringBuilder();
        StringBuilder inProgress = new StringBuilder();
        StringBuilder completed = new StringBuilder();
        for (Task task : tasks) {
            TaskState state = task.getState();
            switch (state) {
                case IN_PROGRESS:
                    inProgress.append(task.getId());
                    inProgress.append(" -- ");
                    inProgress.append(task.getTitle());
                    inProgress.append("\n");
                    break;
                case TODO:
                    toDo.append(task.getId());
                    toDo.append(" -- ");
                    toDo.append(task.getTitle());
                    toDo.append("\n");
                    break;
                default:
                    completed.append(task.getId());
                    completed.append(" -- ");
                    completed.append(task.getTitle());
                    completed.append("\n");
                    break;
            }
        }
        System.out.println(" == To Do == ");
        System.out.println(ANSI_RED + toDo.toString() + ANSI_RESET);
        System.out.println(" == In Progress == ");
        System.out.println(ANSI_YELLOW + inProgress.toString() + ANSI_RESET);
        System.out.println(" == Completed == ");
        System.out.println(ANSI_GREEN + completed.toString() + ANSI_RESET);
    }

    private void deleteTask(){
        printTitle("Borrar una tarea");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.removeTask(new Task(nro));
    }

    private void updateTask(){
        printTitle("Actualizar una tarea");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        System.out.print("Ingrese el nuevo titulo: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el nueva descripcion: ");
        String descripcion = scanner.nextLine();
        this.manager.editTask(nro, title, descripcion);
    }

    private void taskToInProgress(){
        printTitle("Pasar tarea a 'En proceso' ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.taskToInProgress(new Task(nro));
    }

    private void taskToInDone(){
        printTitle("Pasar tarea a 'Completada' ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nro: ");
        long nro = scanner.nextLong();
        this.manager.taskToInDone(new Task(nro));
    }

    private void waitToPressEnter() {
        System.out.println("Presiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            this.cleanner.clean();
        } catch (Exception e) {
            System.out.println("\n ==================================== \n");
        }
    }

    public void runApp(){
        System.out.println(this.manager.wasLoadedNoError());
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        do {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
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
            if (running){
                waitToPressEnter();
            }
        } while (running);
    }
}
