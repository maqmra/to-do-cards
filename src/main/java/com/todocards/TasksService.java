package com.todocards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class TasksService implements Serializable {
    Map<String, Task> tasks = new HashMap<>();

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

    public void save(Path path) {
        List<Task> taskList = new ArrayList<>(tasks.values());
        try {
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task : taskList) {
                Files.writeString(path, task.toCsv(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readCSV(Path path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(String.valueOf(path)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
        sc.useDelimiter("");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] result = s.split(",");
            Task insertedTask = new Task(result[1],Priority.valueOf(result[2]), result[0]);
            add(insertedTask);
        }
        sc.close();  //closes the scanner
    }

}
