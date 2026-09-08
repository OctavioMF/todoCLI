package org.octa;

import org.octa.exceptions.TaskNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public void createTask(String description){
        Date taskTime = new Date();
        Task task = new Task(0, description, TaskStates.TODO, taskTime, taskTime);
        repository.addTask(task);
    }

    public void changeDescription(Integer id, String description){
        Optional<Task> task = repository.getTask(id);
        if (task.isPresent()) {
            Task t = task.get();
            t.setDescription(description);
            repository.updateTask(id, t);
        } else {
            throw new TaskNotFoundException("The task with the id:" + id + " not exist");
        }
    }

    public void deleteTask(Integer id){
        repository.deleteTask(id);
    }

    public void changeStatus(Integer id, TaskStates newStatus){
        Optional<Task> task = repository.getTask(id);
        if (task.isPresent()) {
            Task t = task.get();
            t.setStatus(newStatus);
            repository.updateTask(id, t);
        } else {
            throw new TaskNotFoundException("The task with the id:" + id + " not exist");
        }
    }

    public List<Task> listAllTask(){
        return repository.getAllTasks();
    }

    public List<Task> listTaskWithArguments(TaskStates status){
        return repository.getAllTasks().stream().filter(t -> t.getStatus().equals(status)).toList();
    }
}