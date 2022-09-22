package com.todocards;

public class Main {
    public static void main(String[] args) {
        TasksService service = new TasksService();
        Task firstTask = new Task("Do dishes", "1");
        Task secondTask = new Task("Do the vacuuming", "5");
        Task thirdTask = new Task("Do exercises", "3");
        service.add(firstTask);
        service.add(secondTask);
        service.add(thirdTask);
        System.out.println(service.get());
        Task task = new Task("New task", "6");
        service.update(task);
        System.out.println(service.get());


    }


    public static void sendToFacebook(String task) {
    }

    public static void sendToGoogle(String task) {
    }


}