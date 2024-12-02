package com.example.demoAuth.service;


import com.example.demoAuth.entity.Category;
import com.example.demoAuth.entity.Task;
import com.example.demoAuth.entity.User;
import com.example.demoAuth.repository.CategoryRepository;
import com.example.demoAuth.repository.TaskRepository;
import com.example.demoAuth.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class taskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Task> getAllTasks(String userEmail, String searchText, String categoryTitle, Pageable pageable) {
        // Получаем пользователя
        User user = userRepository.findByEmail(userEmail);

        // Если категория указана, ищем её
        Category category = null;
        if (!categoryTitle.isEmpty()) {
            category = categoryRepository.findByName(categoryTitle);
        }

        // Выполняем фильтрацию по заголовку и категории
        if (searchText != null && !searchText.isEmpty() && category != null) {
            return taskRepository.findByUserAndTitleContainingAndCategory(user.getId(), searchText, category.getId(), pageable);
        }

        if (searchText != null && !searchText.isEmpty()) {
            return taskRepository.findByUserAndTitleContaining(user.getId(), searchText, pageable);
        }

        if (category != null) {
            return taskRepository.findByUserAndCategory(user.getId(), category.getId(), pageable);
        }

        return taskRepository.findByUser(user, pageable);
    }


    public Page<Task> getTaskWithTitle(String title, Pageable pageable) {
        return taskRepository.findByUserAndTitle(null, title, pageable);
    }


    public Task getTask(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // create a task
    public void createTask(Task task) {
        taskRepository.save(task);
    }


    // Update a task by id
    public void updateTask(String id, @Valid Task taskDetails) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDueDate(ValidateDate(taskDetails.getDueDate()));
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setUser(taskDetails.getUser());
        task.setCategory(taskDetails.getCategory());

        taskRepository.save(task);
    }


    // Delete a student by id
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }


    private LocalDate ValidateDate(LocalDate due_date) {
        if (due_date.isBefore(LocalDate.now())) {
            throw new RuntimeException("Due date cannot be in the future");
        }
        return due_date;
    }

}

