package com.nvt.dto;

import java.util.Objects;

public class TaskDto {

    private String workName;

    private String startingDate;

    private String endingDate;

    private String status;

    public TaskDto() {
    }

    public TaskDto(String workName, String startingDate, String endingDate, String status) {
        this.workName = workName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return workName.equals(taskDto.workName) &&
                startingDate.equals(taskDto.startingDate) &&
                endingDate.equals(taskDto.endingDate) &&
                status.equals(taskDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workName, startingDate, endingDate, status);
    }
}
