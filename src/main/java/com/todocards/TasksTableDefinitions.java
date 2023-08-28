package com.todocards;

public interface TasksTableDefinitions {
    String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/todocards";
    String USER = "user";
    String PASSWORD = "password";

    String TABLE_TASKS = "tasks";
    String COLUMN_TASKS_ID = "id";
    String COLUMN_TASKS_CONTENT = "content";
    String COLUMN_TASKS_PRIORITY = "priority";
}
