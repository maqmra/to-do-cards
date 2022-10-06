package com.todocards.domain;

import com.todocards.infrastructure.CsvTaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TasksService extends CsvTaskRepository{
    Map<String, Task> tasks = new HashMap<>();
    TaskRepository taskRepository = new CsvTaskRepository();

    public Task save(Task task) {
        taskRepository.save(task);
        return task;
    }

    public Task update(Task task) {
        taskRepository.update(task);
        return task;
    }

    public void remove(String id) {
        taskRepository.deleteById(id);
    }

    public ArrayList<Task> get() {
        return new ArrayList<>(taskRepository.getAll());
    }

    public void read() {
        for (Task task : taskRepository.getAll()) {
            tasks.put(task.getId(), task);
        }
    }



}
