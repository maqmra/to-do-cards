package com.todocards.repository;

import com.todocards.model.Task;

import java.util.List;

public interface TaskRepository {
    public void createTableTasks();
    public Task add(Task task);
    public Task update(Task task);
    public void remove(Task task);
    public List<Task> getAll();
    Task getById(String id);
}
