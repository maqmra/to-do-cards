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
        service.save(firstTask);
        service.save(secondTask);
        service.save(thirdTask);
        service.save(fourthTask);
        System.out.println(service.get());
        Task task = new Task("New task", "6");
        service.save(task);
    }

}