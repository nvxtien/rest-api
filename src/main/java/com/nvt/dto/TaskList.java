package com.nvt.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<TaskDto> taskDtos;
 
    public TaskList() {
        taskDtos = new ArrayList<>();
    }

    public List<TaskDto> getTaskDtos() {
        return taskDtos;
    }

    public void setTaskDtos(List<TaskDto> taskDtos) {
        this.taskDtos = taskDtos;
    }
}