package com.project.model;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private Priority priority;
    private String dueDate;
    private Status status;
    
    // Getters and setters...
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Gettery i settery dla pola description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Analogicznie dla pozostałych pól: priority, dueDate, status
    // ...

    // Przykładowe gettery i settery dla pól priority i status
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
