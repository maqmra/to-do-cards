package com.todocards.repository;

import com.todocards.model.Task;
import com.todocards.exceptions.CannotCreateTaskException;
import com.todocards.exceptions.CannotUpdateTaskException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksInMemoryRepository implements TaskRepository {
    Map<String, Task> tasks;

    @Override
    public void createTableTasks() {
        tasks = new HashMap<>();
    }

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

    @Override
    public List<Task> getAll() {
        return new ArrayList<>(tasks
                .values());
    }

    @Override
    public Task getById(String id) {
        return tasks.get(id);
    }


}
