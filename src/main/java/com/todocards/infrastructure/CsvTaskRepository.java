package com.todocards.infrastructure;

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
import java.util.Scanner;

public class CsvTaskRepository implements TaskRepository {

    private final Path path = Paths.get("C:\\Users\\Karolina\\Desktop\\tasks.txt");

    @Override
    public List<Task> get() {
        final List<Task> tasks = new ArrayList<>();
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
    public void save(Collection<Task> tasks) {
        List<Task> taskList = new ArrayList<>(tasks);
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

    public String toCsv(Task task) {
        return task.getId() + "," + task.getContent() + "," + task.getPriority() + "\n";
    }
}
