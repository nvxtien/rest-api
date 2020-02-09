package com.nvt.repository;

import com.nvt.model.Status;
import com.nvt.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class TaskRepositoryIT {

    @Autowired
    private TaskRepository repository;

    @Before
    public void init() {
        repository.deleteAllInBatch();
    }

    @Test
    public void addTaskTest() {
        Task task = new Task();
        task.setWorkName("Test");
        task.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task.setStatus(Status.Planning);

        Task result = repository.save(task);
        System.out.println(result.getId());

        Pageable sorted = PageRequest.of(0, 1, Sort.by("workName").ascending());

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        System.out.println(list.get(0).getStartingDate());
        System.out.println(task.getStartingDate());

        assertEquals(list.get(0).getWorkName(), task.getWorkName());
    }

    @Test
    public void updateTaskTest() {
        Task task = new Task();
        task.setWorkName("Test");
        task.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task.setStatus(Status.Planning);

        repository.save(task);

        Pageable sorted = PageRequest.of(0, 1, Sort.by("workName").ascending());
        Page<Task> pages = repository.findAll(sorted);
        List<Task> list= pages.getContent();
        assertNotNull(list);
        assertEquals(list.size(), 1);

        Task update = list.get(0);
        update.setWorkName("Test2");
        update.setEndingDate(java.sql.Date.valueOf("2020-02-03"));

        repository.save(update);

        sorted = PageRequest.of(0, 1, Sort.by("workName").ascending());
        pages = repository.findAll(sorted);
        list= pages.getContent();
        assertNotNull(list);
        assertEquals(list.size(), 1);

        System.out.println(list.get(0).getStartingDate());
        System.out.println(task.getStartingDate());

        assertEquals(list.get(0).getWorkName(), update.getWorkName());
//        assertEquals(list.get(0).getEndingDate().toString(), update.getEndingDate().toString());
    }

    @Test
    public void deleteTaskTest() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "workName").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));
        Page<Task> pages = repository.findAll(sorted);
        List<Task> list = pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 1);

        Long id = list.get(0).getId();
        repository.deleteById(id);

        pages = repository.findAll(sorted);
        list = pages.getContent();
        assertEquals(list.size(), 0);
    }

    @Test
    public void findAllTest_SortByWorkName_ASC() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-02-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-02-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "workName").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task3.getWorkName());
        assertEquals(list.get(1).getWorkName(), task1.getWorkName());
        assertEquals(list.get(2).getWorkName(), task2.getWorkName());
    }

    @Test
    public void findAllTest_SortByWorkName_DES() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-02-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-02-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "workName").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task2.getWorkName());
        assertEquals(list.get(1).getWorkName(), task1.getWorkName());
        assertEquals(list.get(2).getWorkName(), task3.getWorkName());
    }

    @Test
    public void findAllTest_SortByStartingDate_ASC() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-03-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-03-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "startingDate").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task1.getWorkName());
        assertEquals(list.get(1).getWorkName(), task3.getWorkName());
        assertEquals(list.get(2).getWorkName(), task2.getWorkName());
    }

    @Test
    public void findAllTest_SortByStartingDate_DES() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-03-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-03-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "startingDate").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task2.getWorkName());
        assertEquals(list.get(1).getWorkName(), task3.getWorkName());
        assertEquals(list.get(2).getWorkName(), task1.getWorkName());
    }

    @Test
    public void findAllTest_SortByEndingDate_ASC() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-08"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-09"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-03-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-03-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "endingDate").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task3.getWorkName());
        assertEquals(list.get(1).getWorkName(), task1.getWorkName());
        assertEquals(list.get(2).getWorkName(), task2.getWorkName());
    }

    @Test
    public void findAllTest_SortByEndingDate_DES() {
        Task task1 = new Task();
        task1.setWorkName("RESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-08"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-09"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-03-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-03-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "endingDate").ignoreCase();
        Pageable sorted = PageRequest.of(0, 5, Sort.by(order));

        Page<Task> pages = repository.findAll(sorted);

        List<Task> list= pages.getContent();

        assertNotNull(list);
        assertEquals(list.size(), 3);
        list.stream().forEach(x -> System.out.println(x));

        assertEquals(list.get(0).getWorkName(), task2.getWorkName());
        assertEquals(list.get(1).getWorkName(), task1.getWorkName());
        assertEquals(list.get(2).getWorkName(), task3.getWorkName());
    }

    @Test
    public void findAllTest_SortByWorkName_DES_Paging() {
        Task task1 = new Task();
        task1.setWorkName("rESTful service");
        task1.setStartingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setEndingDate(java.sql.Date.valueOf("2020-02-02"));
        task1.setStatus(Status.Planning);
        repository.save(task1);

        Task task2 = new Task();
        task2.setWorkName("Support customer");
        task2.setStartingDate(java.sql.Date.valueOf("2020-02-04"));
        task2.setEndingDate(java.sql.Date.valueOf("2020-02-05"));
        task2.setStatus(Status.Doing);
        repository.save(task2);

        Task task3 = new Task();
        task3.setWorkName("Account");
        task3.setStartingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setEndingDate(java.sql.Date.valueOf("2020-02-06"));
        task3.setStatus(Status.Complete);
        repository.save(task3);

        Task task4 = new Task();
        task4.setWorkName("Transfer");
        task4.setStartingDate(java.sql.Date.valueOf("2020-11-06"));
        task4.setEndingDate(java.sql.Date.valueOf("2020-12-06"));
        task4.setStatus(Status.Complete);
        repository.save(task4);

        Task task5 = new Task();
        task5.setWorkName("payment");
        task5.setStartingDate(java.sql.Date.valueOf("2020-05-07"));
        task5.setEndingDate(java.sql.Date.valueOf("2020-05-12"));
        task5.setStatus(Status.Complete);
        repository.save(task5);

        Task task6 = new Task();
        task6.setWorkName("Report");
        task6.setStartingDate(java.sql.Date.valueOf("2020-07-09"));
        task6.setEndingDate(java.sql.Date.valueOf("2020-07-15"));
        task6.setStatus(Status.Complete);
        repository.save(task6);

        Task task7 = new Task();
        task7.setWorkName("balance");
        task7.setStartingDate(java.sql.Date.valueOf("2020-09-01"));
        task7.setEndingDate(java.sql.Date.valueOf("2020-09-19"));
        task7.setStatus(Status.Complete);
        repository.save(task7);

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "workName").ignoreCase();

        System.out.println("============ page 0 ====================");

        Pageable pageable0 = PageRequest.of(0, 2, Sort.by(order));
        Page<Task> page0 = repository.findAll(pageable0);
        List<Task> list0 = page0.getContent();

        assertNotNull(list0);
        list0.stream().forEach(x -> System.out.println(x));
        assertEquals(list0.size(), 2);
        assertEquals(list0.get(0).getWorkName(), task4.getWorkName());
        assertEquals(list0.get(1).getWorkName(), task2.getWorkName());

        System.out.println("============ page 1 ====================");

        Pageable pageable1 = PageRequest.of(1, 2, Sort.by(order));
        Page<Task> page1 = repository.findAll(pageable1);
        List<Task> list1 = page1.getContent();

        assertNotNull(list1);
        list1.stream().forEach(x -> System.out.println(x));
        assertEquals(list1.size(), 2);
        assertEquals(list1.get(0).getWorkName(), task1.getWorkName());
        assertEquals(list1.get(1).getWorkName(), task6.getWorkName());

        System.out.println("============ page 2 ====================");

        Pageable pageable2 = PageRequest.of(2, 2, Sort.by(order));
        Page<Task> page2 = repository.findAll(pageable2);
        List<Task> list2 = page2.getContent();

        assertNotNull(list2);
        list2.stream().forEach(x -> System.out.println(x));
        assertEquals(list2.size(), 2);
        assertEquals(list2.get(0).getWorkName(), task5.getWorkName());
        assertEquals(list2.get(1).getWorkName(), task7.getWorkName());

        System.out.println("============ page 3 ====================");

        Pageable pageable3 = PageRequest.of(3, 2, Sort.by(order));
        Page<Task> page3 = repository.findAll(pageable3);
        List<Task> list3 = page3.getContent();

        assertNotNull(list3);
        list3.stream().forEach(x -> System.out.println(x));
        assertEquals(list3.size(), 1);
        assertEquals(list3.get(0).getWorkName(), task3.getWorkName());
    }
}
