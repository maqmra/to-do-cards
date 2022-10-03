package com.todocards;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TasksService {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path), false));
            for (Task task : taskList) {
                writer.append(toCsv(task));
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readCsv(Path path) {
        try ( Scanner sc = new Scanner(new File(String.valueOf(path)))){
            sc.useDelimiter("");
            while (sc.hasNext()) {
                String s = sc.nextLine();
                String[] result = s.split(",");
                Task insertedTask = new Task(result[1], Priority.valueOf(result[2]), result[0]);
                add(insertedTask);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }


    public String toCsv(Task task) {
        return task.getId() + "," + task.getContent() + "," + task.getPriority() + "\n";
    }

}
