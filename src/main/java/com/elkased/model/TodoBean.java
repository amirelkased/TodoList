package com.elkased.model;

import java.io.Serializable;
import java.sql.Date;

public class TodoBean implements Serializable {

    private int todo_id;
    private String title;
    private String description;
    private String status;

    private Date date;

    public TodoBean() {
    }

    public TodoBean(String title, String description, String status, Date date) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
    }

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todo_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
