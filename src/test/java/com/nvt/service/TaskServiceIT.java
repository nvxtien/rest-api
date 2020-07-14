package com.nvt.service;

import com.nvt.dto.TaskDto;
import com.nvt.orm.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceIT {

    @Autowired
    @Qualifier("taskService")
    TaskService taskService;

    @Before
    public void init() {
        taskService.deleteAllInBatch();
    }

    @Test
    public void addTaskTest() {
        TaskDto taskDto = new TaskDto();
        taskDto.setWorkName("Test");
        taskDto.setStartingDate("2020-02-02");
        taskDto.setEndingDate("2020-02-02");
        taskDto.setStatus("Planning");

        Task result = taskService.save(taskDto);
        System.out.println(result.toString());

        List<Task> list = taskService.getAllTasks(0, 1, "workName", "ASC");

        assertNotNull(list);
        assertEquals(list.size(), 1);

        System.out.println(list.get(0).getStartingDate());
        System.out.println(taskDto.getStartingDate());

        assertEquals(list.get(0).getWorkName(), taskDto.getWorkName());
        assertEquals(list.get(0).getStatus().toString(), taskDto.getStatus());
//        assertEquals(list.get(0).getStartingDate().toString(), taskDto.getStartingDate());
    }

    @Test
    public void findAllTest_SortByWorkName_DES() {

        TaskDto taskDto1 = new TaskDto();
        taskDto1.setWorkName("Support customer");
        taskDto1.setStartingDate("2020-02-02");
        taskDto1.setEndingDate("2020-02-02");
        taskDto1.setStatus("Planning");
        taskService.save(taskDto1);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setWorkName("Test");
        taskDto2.setStartingDate("2020-03-04");
        taskDto2.setEndingDate("2020-03-05");
        taskDto2.setStatus("Planning");
        taskService.save(taskDto2);

        TaskDto taskDto3 = new TaskDto();
        taskDto3.setWorkName("Account");
        taskDto3.setStartingDate("2020-03-04");
        taskDto3.setEndingDate("2020-03-05");
        taskDto3.setStatus("Planning");
        taskService.save(taskDto3);


        List<Task> list = taskService.getAllTasks(0, 5, "workName", "DESC");

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), taskDto2.getWorkName());
        assertEquals(list.get(1).getWorkName(), taskDto1.getWorkName());
        assertEquals(list.get(2).getWorkName(), taskDto3.getWorkName());
    }

    @Test
    public void findAllTest_SortByWorkName_DES_Paging() {

        TaskDto task1 = new TaskDto();
        task1.setWorkName("rESTful service");
        task1.setStartingDate("2020-02-02");
        task1.setEndingDate("2020-02-02");
        task1.setStatus("Planning");
        taskService.save(task1);

        TaskDto task2 = new TaskDto();
        task2.setWorkName("Support customer");
        task2.setStartingDate("2020-02-04");
        task2.setEndingDate("2020-02-05");
        task2.setStatus("Doing");
        taskService.save(task2);

        TaskDto task3 = new TaskDto();
        task3.setWorkName("Account");
        task3.setStartingDate("2020-02-06");
        task3.setEndingDate("2020-02-06");
        task3.setStatus("Complete");
        taskService.save(task3);

        TaskDto task4 = new TaskDto();
        task4.setWorkName("Transfer");
        task4.setStartingDate("2020-11-06");
        task4.setEndingDate("2020-12-06");
        task4.setStatus("Complete");
        taskService.save(task4);

        TaskDto task5 = new TaskDto();
        task5.setWorkName("payment");
        task5.setStartingDate("2020-05-07");
        task5.setEndingDate("2020-05-12");
        task5.setStatus("Complete");
        taskService.save(task5);

        TaskDto task6 = new TaskDto();
        task6.setWorkName("Report");
        task6.setStartingDate("2020-07-09");
        task6.setEndingDate("2020-07-15");
        task6.setStatus("Complete");
        taskService.save(task6);

        TaskDto task7 = new TaskDto();
        task7.setWorkName("balance");
        task7.setStartingDate("2020-09-01");
        task7.setEndingDate("2020-09-19");
        task7.setStatus("Complete");
        taskService.save(task7);

        System.out.println("============ page 0 ====================");
        List<Task> list0 = taskService.getAllTasks(0, 2, "workName", "DESC");

        assertNotNull(list0);
        list0.stream().forEach(x -> System.out.println(x));
        assertEquals(list0.size(), 2);
        assertEquals(list0.get(0).getWorkName(), task4.getWorkName());
        assertEquals(list0.get(1).getWorkName(), task2.getWorkName());

        System.out.println("============ page 1 ====================");

        List<Task> list1 = taskService.getAllTasks(1, 2, "workName", "DESC");

        assertNotNull(list1);
        list1.stream().forEach(x -> System.out.println(x));
        assertEquals(list1.size(), 2);
        assertEquals(list1.get(0).getWorkName(), task1.getWorkName());
        assertEquals(list1.get(1).getWorkName(), task6.getWorkName());

        System.out.println("============ page 2 ====================");

        List<Task> list2 = taskService.getAllTasks(2, 2, "workName", "DESC");

        assertNotNull(list2);
        list2.stream().forEach(x -> System.out.println(x));
        assertEquals(list2.size(), 2);
        assertEquals(list2.get(0).getWorkName(), task5.getWorkName());
        assertEquals(list2.get(1).getWorkName(), task7.getWorkName());

        System.out.println("============ page 3 ====================");

        List<Task> list3 = taskService.getAllTasks(3, 2, "workName", "DESC");

        assertNotNull(list3);
        list3.stream().forEach(x -> System.out.println(x));
        assertEquals(list3.size(), 1);
        assertEquals(list3.get(0).getWorkName(), task3.getWorkName());
    }
}
