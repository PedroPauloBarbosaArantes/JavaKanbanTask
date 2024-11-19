package org.example.repository;

import org.example.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Status status);
    List<Task> findByPriority(Task.Priority priority);
    List<Task> findByDueDateBeforeAndStatusNot(LocalDateTime dueDate, Task.Status status);

}

