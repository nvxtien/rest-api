package com.nvt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nvt.dto.TaskDto;
import com.nvt.dto.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIT {

    private static final ObjectMapper om = new ObjectMapper();

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void addTeskTest() {

        TaskDto task = new TaskDto();
        task.setWorkName("Corona");
        task.setStartingDate("2019-12-01");
        task.setEndingDate("2020-12-01");
        task.setStatus("Planning");
        HttpEntity<TaskDto> entity = new HttpEntity<>(task, new HttpHeaders());
        ResponseEntity<TaskDto> response = template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity, TaskDto.class);

        System.out.println(response.getBody().getWorkName());

        assertEquals(response.getBody().equals(task), true);

    }

    @Test
    public void sortAndPagingTest() {

        TaskDto task = new TaskDto();
        task.setWorkName("Corona");
        task.setStartingDate("2019-12-01");
        task.setEndingDate("2020-12-01");
        task.setStatus("Planning");

        TaskDto task2 = new TaskDto();
        task2.setWorkName("BCorona");
        task2.setStartingDate("2019-12-01");
        task2.setEndingDate("2020-12-01");
        task2.setStatus("Planning");

        TaskDto task3 = new TaskDto();
        task3.setWorkName("TCorona");
        task3.setStartingDate("2019-12-01");
        task3.setEndingDate("2020-12-01");
        task3.setStatus("Planning");

        TaskDto task4 = new TaskDto();
        task4.setWorkName("SCorona");
        task4.setStartingDate("2019-12-01");
        task4.setEndingDate("2020-12-01");
        task4.setStatus("Planning");

        TaskDto task5 = new TaskDto();
        task5.setWorkName("LCorona");
        task5.setStartingDate("2019-12-01");
        task5.setEndingDate("2020-12-01");
        task5.setStatus("Planning");

        HttpEntity<TaskDto> entity1 = new HttpEntity<>(task, new HttpHeaders());
        template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity1, TaskDto.class);

        HttpEntity<TaskDto> entity2 = new HttpEntity<>(task2, new HttpHeaders());
        template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity2, TaskDto.class);

        HttpEntity<TaskDto> entity3 = new HttpEntity<>(task3, new HttpHeaders());
        template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity3, TaskDto.class);

        HttpEntity<TaskDto> entity4 = new HttpEntity<>(task4, new HttpHeaders());
        template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity4, TaskDto.class);

        HttpEntity<TaskDto> entity5 = new HttpEntity<>(task5, new HttpHeaders());
        template.exchange("http://localhost:" + port + "/api/task/add",
                HttpMethod.POST, entity5, TaskDto.class);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/task/")
                .queryParam("page","0")
                .queryParam("size","2")
                .queryParam("sortBy","workName")
                .queryParam("asc", "ASC").build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<TaskList> response = template.exchange(builder.toUriString(),HttpMethod.GET, request, TaskList.class);

        TaskList taskList = response.getBody();

        assertEquals(taskList != null, true);

        System.out.println(taskList.getTaskDtos().size());

//        List<TaskDto> taskDtos = response.getBody().getTaskDtos();
//        System.out.println(taskDtos.get(0).toString());
//        taskDtos.stream().forEach(x -> System.out.println(x));
//        assertEquals(response.getBody().equals(task), true);

    }

}
