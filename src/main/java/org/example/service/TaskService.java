package org.example.service;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus(Task.Status.A_FAZER);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(now, Task.Status.CONCLUIDO);
    }


    public Task moveTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            switch (task.getStatus()) {
                case A_FAZER:
                    task.setStatus(Task.Status.EM_PROGRESSO);
                    break;
                case EM_PROGRESSO:
                    task.setStatus(Task.Status.CONCLUIDO);
                    break;
                default:
                    throw new IllegalStateException("Não é possível mover a tarefa.");
            }
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Tarefa não encontrada.");
        }
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        taskRepository.delete(task);
    }
}

