package com.todocards.infrastructure;

import com.todocards.domain.CannotCreateTaskException;
import com.todocards.domain.CannotUpdateTaskException;
import com.todocards.domain.Priority;
import com.todocards.domain.Task;
import com.todocards.domain.TaskRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CsvTaskRepository implements TaskRepository {

    private final Path path = Paths.get("C:\\Users\\Karolina\\Desktop\\tasks.txt");

    @Override
    public List<Task> getAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(String.valueOf(path)))) {
            sc.useDelimiter("");
            while (sc.hasNext()) {
                String s = sc.nextLine();
                String[] result = s.split(",");
                Task insertedTask = new Task(result[1], Priority.valueOf(result[2]), result[0]);
                tasks.add(insertedTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    @Override
    public Task save(Collection<Task> tasks) {
        List<Task> taskList = new ArrayList<>(tasks);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path), true));) {
            for (Task task : taskList) {
                if (!taskExists(task)) {
                    writer.append(toCsv(task));
                    return task;
                } else {
                    throw new CannotCreateTaskException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Task save(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(path), true));) {
            if (!taskExists(task)) {
                writer.append(toCsv(task));
                return task;
            } else {
                throw new CannotCreateTaskException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean taskExists(Task task) {
        for (Task taskFromList : getAll()) {
            if (Objects.equals(taskFromList.getId(), task.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean taskExists(String id) {
        for (Task taskFromList : getAll()) {
            if (Objects.equals(taskFromList.getId(), id)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Task update(Task task) {
        if (taskExists(task)) {
            getAll().set(getAll().indexOf(task), task);

        } else {
            throw new CannotUpdateTaskException();
        }
        return task;
    }

    @Override
    public boolean deleteById(String id) {
        for (Task task : getAll()) {
            if (Objects.equals(task.getId(), id)) {
                getAll().remove(task);
                return true;
            }
            return false;
        }
        return false;
    }

    public String toCsv(Task task) {
        return task.getId() + "," + task.getContent() + "," + task.getPriority() + "\n";
    }
}
