package com.nvt.controllers;

import com.nvt.dto.TaskDto;
import com.nvt.dto.TaskList;
import com.nvt.model.Task;
import com.nvt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody TaskDto req) {
        Task task = taskService.save(req);
        if (task.getId() != null) {
            return new ResponseEntity<>(req, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TaskDto(), HttpStatus.EXPECTATION_FAILED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long taskId,
                           @RequestBody TaskDto req) {

        Task task = taskService.update(taskId, req);
        if (task.getId() != null) {
            return new ResponseEntity<>(req, HttpStatus.OK);
        }
        return new ResponseEntity<>(new TaskDto(), HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long taskId) {

        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<TaskList> getAllTasks(@PathVariable(value = "page") Integer page,
                                                @PathVariable(value = "size") Integer size,
                                                @PathVariable(value = "sortBy") String sortBy,
                                                @PathVariable(value = "asc") String asc) {

        List<Task> list = taskService.getAllTasks(page, size, sortBy, asc);
        Function<Task, TaskDto> converter = task -> new TaskDto(task.getWorkName(),
                task.getStartingDate().toString(),
                task.getEndingDate().toString(),
                task.getStatus().toString());

        List<TaskDto> taskDtos = list.stream().map(converter).collect(toList());
        TaskList taskList = new TaskList();
        taskList.setTaskDtos(taskDtos);
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
}