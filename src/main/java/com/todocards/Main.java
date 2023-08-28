package com.todocards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {


        TaskRepository repository = new TaskJdbcRepository();
        repository.createTableTasks();


        Task firstTask = new Task("Do dishes", "1");
        Task secondTask = new Task("Do the vacuuming", "5");
        Task thirdTask = new Task("Do exercises", "3");


        repository.add(firstTask);
        repository.add(secondTask);
        repository.add(thirdTask);


        Task task = new Task("Walk the dog", "5");
        repository.update(task);
        repository.remove(task);
        System.out.println(repository.getAll().toString());
        System.out.println(repository.getById("3"));

    }
}