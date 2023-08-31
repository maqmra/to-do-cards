package com.todocards.repository;

import com.todocards.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskJdbcRepository implements TaskRepository, TasksTableDefinitions {

    private Connection conn;
    private Statement statement;

    public TaskJdbcRepository() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Can't create connection. " + e.getMessage());
        }
    }


    public void createTableTasks() {
        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " (" +
                    COLUMN_TASKS_ID + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUMN_TASKS_CONTENT + " TEXT NOT NULL, " +
                    COLUMN_TASKS_PRIORITY + " TEXT NOT NULL)");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't create new table. " + e.getMessage());
        }
    }

    @Override
    public Task add(Task task) {
        try {
            statement = conn.createStatement();

            String query = "INSERT INTO " + TABLE_TASKS + "(" +
                    COLUMN_TASKS_ID + ", " +
                    COLUMN_TASKS_CONTENT + ", " +
                    COLUMN_TASKS_PRIORITY + ") VALUES (" +
                    task.getId() + ", '" + task.getContent() + "', '" + task.getPriority() + "')";
            System.out.println("Query = " + query);

            statement.execute(query);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't add new task to database. " + e.getMessage());
        }
        return task;
    }

    @Override
    public Task update(Task task) {
        try {
            statement = conn.createStatement();

            String query = "UPDATE " + TABLE_TASKS +
                    " SET " +
                    COLUMN_TASKS_CONTENT + " = '" + task.getContent() + "', " +
                    COLUMN_TASKS_PRIORITY + " = '" + task.getPriority() + "' " +
                    "WHERE " + COLUMN_TASKS_ID + " = '" + task.getId() + "'";

            System.out.println("Query = " + query);

            statement.execute(query);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't update task. " + e.getMessage());
        }
        return task;
    }

    @Override
    public void remove(Task task) {
        try {
            statement = conn.createStatement();
            String query = "DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASKS_ID + " = '" + task.getId() + "'";
            System.out.println("Query = " + query);
            statement.execute(query);

            statement.close();
        } catch (SQLException e) {
            System.out.println("Can't delete task. " + e.getMessage());
        }


    }

    @Override
    public List<Task> getAll() {
        try {
            statement = conn.createStatement();
            String query = "SELECT * FROM " + TABLE_TASKS;
            System.out.println("Query = " + query);
            ResultSet result = statement.executeQuery(query);

            List<Task> tasks = new ArrayList<>();
            while (result.next()) {
                Task task = new Task(result.getString(COLUMN_TASKS_CONTENT), result.getString(COLUMN_TASKS_ID));
                tasks.add(task);
            }

            statement.close();

            return tasks;

        } catch (SQLException e) {
            System.out.println("Can't select tasks. " + e.getMessage());
        }
        return null;

    }

    @Override
    public Task getById(String id) {

        try {
            statement = conn.createStatement();
            String query = "SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASKS_ID + " = '" + id + "'";
            System.out.println("Query = " + query);
            ResultSet result = statement.executeQuery(query);

//            Task task = new Task(result.getString(COLUMN_CONTENT), result.getString(COLUMN_PRIORITY), result.getString(COLUMN_ID));
            result.next();
            Task task = new Task(result.getString(COLUMN_TASKS_CONTENT), result.getString(COLUMN_TASKS_ID));

            statement.close();
            return task;
        } catch (SQLException e) {
            System.out.println("Can't select task " + id + ". " + e.getMessage());
        }

        return null;
    }
}
