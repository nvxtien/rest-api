package com.nvt.controllers;

import com.nvt.ApiException;
import com.nvt.model.Task;
import com.nvt.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/add")
    public Task add(@Valid @RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable(value = "id") Long taskId,
                           @Valid @RequestBody Task req) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ApiException("Task", "id", taskId));

        task.setStatus(req.getStatus());
        task.setStartingDate(req.getStartingDate());
        task.setEndingDate(req.getEndingDate());

        Task update = taskRepository.save(task);
        return update;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ApiException("Task", "id", taskId));

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}