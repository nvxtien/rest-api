package com.nvt.repository;

import com.nvt.orm.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("taskRepository")
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByWorkName(String name);

    Page<Task> findAll(Pageable pageable);

}