package com.todocards.domain;

import java.util.Collection;
import java.util.List;

public interface TaskRepository {

    List<Task> getAll();

    Task save(Collection<Task> tasks);
    Task save(Task task);
    Task update(Task task);
    boolean deleteById(String id);
}
