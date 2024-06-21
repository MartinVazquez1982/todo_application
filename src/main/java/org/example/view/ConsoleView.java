package org.example.view;

import org.example.system.Task;
import org.example.system.TaskAdministrator;
import org.example.system.TaskState;
import org.example.view.cleanConsole.CleanConsole;
import org.example.view.cleanConsole.LinuxCleanner;
import org.example.view.cleanConsole.WindowsCleanner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 *  This class is responsible for console display
 */
public class ConsoleView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private TaskAdministrator manager;
    private CleanConsole cleanner;

    /**
     * It creates a console display
     *
     * @param manager The manager is the administrator of the tasks
     */
    public ConsoleView(TaskAdministrator manager) {
        this.manager = manager;
        if (System.getProperty("os.name").contains("Windows")) {
            this.cleanner = new WindowsCleanner();
        } else {
            this.cleanner = new LinuxCleanner();
        }
    }

    /**
     * It shows a section title
     *
     * @param title The title to display
     */
    private void printTitle(String title) {
        System.out.println("\n" + ANSI_BLUE + " ===> " + title + " <=== " + ANSI_RESET);
    }

    /**
     * It shows the menu
     */
    private void displayMenu() {
        printTitle("MENU DE TO DO");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Detalle de una tarea");
        System.out.println("4. Actualizar tarea");
        System.out.println("5. Eliminar tarea");
        System.out.println("6. Marcar tarea como 'En proceso'");
        System.out.println("7. Marcar tarea como 'Completada'");
        System.out.println("8. Salir");
        System.out.print("Elija una opción: ");
    }

    /**
     * It does the display for creating a task
     */
    private void createTask(){
        printTitle("Crear tarea");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el titulo: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el descripcion: ");
        String descripcion = scanner.nextLine();
        this.manager.addTask(new Task(title,descripcion));
    }

    /**
     * Join tasks to a string
     *
     * @param list The list is where tasks are added
     * @param task Task to add
     */
    private void loadOutputStateList(StringBuilder list, Task task){
        list.append(" ");
        list.append(task.getId());
        list.append(" | ");
        list.append(task.getTitle());
        list.append("\n");
    }

    /**
     * It shows the list task
     */
    private void listTasks(){
        List<Task> tasks = this.manager.getTasks();
        printTitle("Lista de Tareas");
        StringBuilder toDo = new StringBuilder();
        StringBuilder inProgress = new StringBuilder();
        StringBuilder completed = new StringBuilder();
        for (Task task : tasks) {
            TaskState state = task.getState();
            switch (state) {
                case IN_PROGRESS:
                    loadOutputStateList(inProgress, task);
                    break;
                case TODO:
                    loadOutputStateList(toDo, task);
                    break;
                default:
                    loadOutputStateList(completed, task);
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

    /**
     * It requests the task number
     *
     * @return Task number
     */
    private long requestId(){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        long salida = -1;
        do {
            try {
                System.out.print(" Ingrese el nro: ");
                salida = scanner.nextLong();
                running = false;
            } catch(Exception e){
                scanner.nextLine();
                System.out.println(ANSI_RED + "\n Entrada no valida \n" + ANSI_RESET);
            }
        } while (running);
        return salida;
    }

    /**
     * It shows the details of the task
     */
    private void taskDetail() {
        printTitle("Detalle de una tarea");
        long nro = requestId();
        Optional<Task> task = this.manager.getTask(new Task(nro));
        if (task.isPresent()) {
            System.out.println(task.get());
        } else {
            System.out.println("No existe la tarea");
        }
    }

    /**
     * It does the display for delete a task
     */
    private void deleteTask(){
        printTitle("Borrar una tarea");
        long nro = requestId();
        this.manager.removeTask(new Task(nro));
    }

    /**
     * It does the display for update a task
     */
    private void updateTask(){
        printTitle("Actualizar una tarea");
        Scanner scanner = new Scanner(System.in);
        long nro = requestId();
        Optional<Task> task = this.manager.getTask(new Task(nro));
        if (task.isPresent()) {
            System.out.println(task.get());
            System.out.println("\n Ingrese los cambios: \n");
            System.out.print(" =Ingrese el nuevo titulo= ");
            String title = scanner.nextLine();
            System.out.print(" Ingrese la nueva descripcion: ");
            String descripcion = scanner.nextLine();
            this.manager.editTask(nro, title, descripcion);
        } else {
            System.out.println("No existe la tarea");
        }
    }

    /**
     * It marks a task In Progress
     */
    private void taskToInProgress(){
        printTitle("Pasar tarea a 'En proceso' ");
        long nro = requestId();
        this.manager.taskToInProgress(new Task(nro));
    }

    /**
     * It marks a task in Done
     */
    private void taskToInDone(){
        printTitle("Pasar tarea a 'Completada' ");
        long nro = requestId();
        this.manager.taskToInDone(new Task(nro));
    }

    /**
     * Shows and waits to press enter
     */
    private void waitToPressEnter() {
        System.out.println("\nPresiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            this.cleanner.clean();
        } catch (Exception e) {
            System.out.println("\n ==================================== \n");
        }
    }

    /**
     * Run the application
     */
    public void runApp(){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Code to be executed when pressing Ctrl+C
            printTitle(" Saliendo de la aplicación ");
            try {
                this.manager.storeTasks();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        try{
            this.cleanner.clean();
        } catch (Exception e) {}
        do {
            displayMenu();
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = -1;
            } finally {
                scanner.nextLine();
            }
            try{
                this.cleanner.clean();
            } catch (Exception e) {}
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    taskDetail();
                    break;
                case 4:
                    updateTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    taskToInProgress();
                    break;
                case 7:
                    taskToInDone();
                    break;
                case 8:
                    running = false;
                    try{
                        this.manager.storeTasks();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    printTitle(" Saliendo de la aplicación ");
                    break;
                default:
                    System.out.println(ANSI_RED +" Opción no válida. Por favor, elija una opción del 1 al 8. " + ANSI_RESET);
            }
            if (running){
                waitToPressEnter();
            }
        } while (running);
    }
}
