package com.todocards.domain;

import com.todocards.infrastructure.CsvTaskRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TasksService {
    Map<String, Task> tasks = new HashMap<>();
    TaskRepository taskRepository = new CsvTaskRepository();

    public Task add(Task task) {
        if ((task.getId() == null) || (taskIdExist(task.getId()))) {
            throw new CannotCreateTaskException();
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public Task update(Task task) {
        if ((task.getId() == null) || (!taskIdExist(task.getId()))) {
            throw new CannotUpdateTaskException();
        }
        tasks.replace(task.getId(), task);
        return task;
    }

    private boolean taskIdExist(String id) {
        return tasks.containsKey(id);
    }

    public void remove(Task task) {
        tasks.remove(task.getId());
    }

    public ArrayList<Task> get() {
        return new ArrayList<>(tasks.values());
    }

    public void save() {
        taskRepository.save(tasks.values());
    }

    public void read() {
        for (Task task : taskRepository.get()) {
            tasks.put(task.getId(), task);
        }
    }



}
