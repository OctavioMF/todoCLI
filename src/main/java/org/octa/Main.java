package org.octa;

import org.octa.exceptions.TaskNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        if (args.length < 1) {
            System.out.println("Error: No command provided.");
            return;
        }

        String command = args[0];
        TodoService service = new TodoService();

        try {
            switch (command){
                case "add":
                    if (args.length > 1 && !args[1].trim().isEmpty()){
                        service.createTask(args[1]);
                    } else {
                        System.out.println("Error: Missing description for add command.");
                    }
                    break;
                case "update":
                    if (args.length > 2 && !args[1].isEmpty() && !args[2].trim().isEmpty()){
                        service.changeDescription(Integer.parseInt(args[1]), args[2]);
                    } else {
                        System.out.println("Error: Missing ID or description for update command.");
                    }
                    break;
                case "delete":
                    if (args.length > 1 && !args[1].isEmpty()){
                        service.deleteTask(Integer.parseInt(args[1]));
                    }
                    break;
                case "mark-in-progress":
                    if (args.length > 1 && !args[1].isEmpty()){
                        service.changeStatus(Integer.parseInt(args[1]), TaskStates.IN_PROGRESS);
                    }
                    break;
                case "mark-done":
                    if (args.length > 1 && !args[1].isEmpty()){
                        service.changeStatus(Integer.parseInt(args[1]), TaskStates.DONE);
                    }
                    break;
                case "list":
                    if (args.length == 1) {
                        service.listAllTask().forEach(System.out::println);
                    } else if (args.length > 1) {
                        List<Task> tasks = null;
                        switch (args[1]) {
                            case "done":
                                tasks = service.listTaskWithArguments(TaskStates.DONE);
                                break;
                            case "todo":
                                tasks = service.listTaskWithArguments(TaskStates.TODO);
                                break;
                            case "in-progress":
                                tasks = service.listTaskWithArguments(TaskStates.IN_PROGRESS);
                                break;
                            default:
                                System.out.println("Status not recognized. Use: done, todo, or in-progress.");
                        }
                        if (tasks != null) {
                            tasks.forEach(System.out::println);
                        }
                    }
                    break;
                default:
                    System.out.println("command not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: the task id must be a number");
        } catch (TaskNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("System internal error: " + e.getMessage());
        }
    }
}