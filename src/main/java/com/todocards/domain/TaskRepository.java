package com.todocards.domain;

import java.util.Collection;
import java.util.List;

public interface TaskRepository {
    List<Task> get();
    void save(Collection<Task> tasks);
}
