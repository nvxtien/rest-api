package com.nvt.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String workName;

    @NotBlank
    private Date startingDate;

    @NotBlank
    private Date endingDate;

    @NotBlank
    private Status status;

    public Task() {
    }

    public Task(String workName, Date startingDate, Date endingDate, Status status) {
        this.workName = workName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", workName='" + workName + '\'' +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", status=" + status +
                '}';
    }
}