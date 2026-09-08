package org.octa;

import java.util.Date;

public class Task {
    private Integer id;
    private String description;
    private TaskStates status;
    private Date createdAt;
    private Date updatedAt;

    public Task(){}

    public Task(Integer id, String description, TaskStates status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStates getStatus() {
        return status;
    }

    public void setStatus(TaskStates status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return id + ": " + description + " [" + status + "]";
    }
}
