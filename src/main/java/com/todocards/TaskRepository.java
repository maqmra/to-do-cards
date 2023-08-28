package com.todocards;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TaskRepository {
    public void createTableTasks();
    public Task add(Task task);
    public Task update(Task task);
    public void remove(Task task);
    public List<Task> getAll();
    Task getById(String id);
}
