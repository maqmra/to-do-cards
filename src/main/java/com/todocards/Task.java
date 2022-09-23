package com.todocards;

public class Task {
    private final String content;
    private final Priority priority;
    private final String id;
    public Task(String content, Priority priority, String id) {
        this.content = content;
        this.priority = priority;
        this.id = id;
    }

    public Task(String content, String id) {
        this(content, Priority.Low, id);
    }

    public String getContent() {
        return content;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return content + ". Priority: " + priority;
    }

    public String toCsv() {
        return getId() + "," + getContent() + "," + getPriority() + "\n";
    }
}
