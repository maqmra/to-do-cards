package com.todocards;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        TasksService service = new TasksService();
        Task firstTask = new Task("Do dishes", "1");
        Task secondTask = new Task("Do the vacuuming", "5");
        Task thirdTask = new Task("Do exercises", "3");
        service.add(firstTask);
        service.add(secondTask);
        service.add(thirdTask);
        System.out.println(service.get());
        Task task = new Task("New task", "5");
        service.update(task);
        System.out.println(service.get());
        Path path = Paths.get("C:\\Users\\Karolina\\Desktop\\tasks.txt");
//        service.save(path, service.get());
//        try {
//            Files.writeString(path, "aa", StandardOpenOption.CREATE);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }


    public static void sendToFacebook(String task) {
    }

    public static void sendToGoogle(String task) {
    }


}