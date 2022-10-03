package com.todocards;

import com.todocards.domain.Task;
import com.todocards.domain.TasksService;

public class Main {
    public static void main(String[] args) {
        TasksService service = new TasksService();
        Task firstTask = new Task("Do dishes", "1");
        Task secondTask = new Task("Do the vacuuming", "5");
        Task thirdTask = new Task("Do exercises", "3");
        Task fourthTask = new Task("Smile", "4");
        service.add(firstTask);
        service.add(secondTask);
        service.add(thirdTask);
        service.add(fourthTask);
        System.out.println(service.get());
        Task task = new Task("New task", "5");
        Task fifthTask = new Task("Aaaa", "6");
        service.update(task);
        service.add(fifthTask);
        service.remove(firstTask);
        System.out.println(service.get());
        service.save();
        TasksService readService = new TasksService();
        readService.read();
        System.out.println("New task list");
        System.out.println(readService.get());

    }

}