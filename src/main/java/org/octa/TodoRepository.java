package org.octa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.octa.exceptions.TaskNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TodoRepository {
    private final Path jsonPath = Paths.get("Todo.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private void createJson() throws IOException {
        Files.writeString(jsonPath, "[]");
    }

    public void addTask(Task task){
        try{
            if(!Files.exists(jsonPath)){createJson();}

            List<Task> todo = mapper.readValue(jsonPath.toFile(), new TypeReference<List<Task>>(){});

            Integer id = todo.stream().mapToInt(Task::getId).max().orElse(0) + 1;
            task.setId(id);

            todo.add(task);

            mapper.writeValue(jsonPath.toFile(), todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(Integer id){
        try {
            if(!Files.exists(jsonPath)){throw new TaskNotFoundException("The task with the id:" + id.toString() + " not exist");}

            List<Task> todo = mapper.readValue(jsonPath.toFile(), new TypeReference<List<Task>>(){});

            boolean wasRemoved = todo.removeIf(t -> t.getId().equals(id));

            if(wasRemoved){
                mapper.writeValue(jsonPath.toFile(), todo);
            }else{
                throw new TaskNotFoundException("The task with the id:" + id.toString() + " not exist");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks(){
        try{
            if(!Files.exists(jsonPath)){return new ArrayList<>();}
            return mapper.readValue(jsonPath.toFile(), new TypeReference<List<Task>>(){});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Task> getTask(Integer id){
        try {
            if(!Files.exists(jsonPath)){return Optional.empty();}

            List<Task> todo = mapper.readValue(jsonPath.toFile(), new TypeReference<List<Task>>(){});

            return todo.stream().filter(t -> t.getId().equals(id)).findFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTask(Integer id, Task newTask){
        try {
            if(!Files.exists(jsonPath)){throw new TaskNotFoundException("The task with the id:" + id.toString() + " not exist");}

            List<Task> todo = mapper.readValue(jsonPath.toFile(), new TypeReference<List<Task>>(){});

            Optional<Task> task = todo.stream().filter(t -> t.getId().equals(id)).findFirst();

            if (task.isPresent()){
                Task oldTask = task.get();

                oldTask.setDescription(newTask.getDescription());
                oldTask.setStatus(newTask.getStatus());
                oldTask.setUpdatedAt(new Date());

                mapper.writeValue(jsonPath.toFile(), todo);
            }else{
                throw new TaskNotFoundException("The task with the id:" + id.toString() + " not exist");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
