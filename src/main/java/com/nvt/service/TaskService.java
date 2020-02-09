package com.nvt.service;

import com.nvt.ApiException;
import com.nvt.dto.TaskDto;
import com.nvt.model.Status;
import com.nvt.model.Task;
import com.nvt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Qualifier("taskService")
@Component
public class TaskService {

    @Qualifier("taskRepository")
    @Autowired
    public TaskRepository taskRepository;

    public Task save(TaskDto req) {
        Task task = new Task();
        return saveOrUpdateTask(req, task);
    }

    public Task update(Long taskId, TaskDto req) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ApiException("Task", "id", taskId));
        return saveOrUpdateTask(req, task);
    }

    public void delete(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ApiException("Task", "id", taskId));

        taskRepository.delete(task);
    }

    public List<Task> getAllTasks(Integer page, Integer size, String sortBy, String asc) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortBy).ignoreCase();
        if (asc.equals("DESC")) {
            order = new Sort.Order(Sort.Direction.DESC, sortBy).ignoreCase();
        }

        Pageable sorted = PageRequest.of(page, size, Sort.by(order));

        Page<Task> tasks = taskRepository.findAll(sorted);

        List<Task> taskList = new ArrayList<>();

        if (tasks != null && tasks.getTotalElements() >= 0) {
            taskList = tasks.getContent();
        }

        return taskList;
    }

    private Task saveOrUpdateTask(TaskDto req, Task task) {
        task.setWorkName(req.getWorkName());
        task.setStartingDate(java.sql.Date.valueOf(req.getStartingDate()));
        task.setEndingDate(java.sql.Date.valueOf(req.getEndingDate()));
        task.setStatus(Status.valueOf(req.getStatus()));
        Task result = taskRepository.save(task);
        if (result != null) {
            return task;
        } else {
            return new Task();
        }
    }

    public void deleteAllInBatch() {
        taskRepository.deleteAllInBatch();
    }
}
