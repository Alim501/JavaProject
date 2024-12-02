package com.example.demoAuth.controller;

import com.example.demoAuth.entity.Category;
import com.example.demoAuth.entity.Task;
import com.example.demoAuth.entity.User;
import com.example.demoAuth.service.CategoryService;
import com.example.demoAuth.service.taskService;
import com.example.demoAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private taskService notesService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    @GetMapping("")
    public String getTasks(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "4") int size,
            @RequestParam(value = "categoryTitle",defaultValue = "") String categoryTitle,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user_email = authentication.getName();
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage = notesService.getAllTasks(user_email, search, categoryTitle, pageable);

        model.addAttribute("tasks", taskPage); // передаем объект Page в модель
        model.addAttribute("currentPage", taskPage.getNumber()); // текущая страница
        model.addAttribute("totalPages", taskPage.getTotalPages()); // общее количество страниц
        model.addAttribute("totalElements", taskPage.getTotalElements()); // общее количество задач
        model.addAttribute("search", search); // сохраняем параметр поиска
        model.addAttribute("categories", categoryService.getCategories()); // категории

        return "tasks";
    }


    // Create a new task
    @PostMapping("")
    public String createTask(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam String dueDate,
                             @RequestParam Integer priority,
                             @RequestParam String categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(LocalDate.parse(dueDate));
        task.setPriority(priority);
        task.setUser(user);
        Category category = categoryService.getCategory(categoryId);
        task.setCategory(category);

        notesService.createTask(task);
        return "redirect:/task";
    }


    // Fetch a single task
    @GetMapping("/{id}")
    public String getTask(Model model, @PathVariable String id) {
        model.addAttribute("task", notesService.getTask(id));
        model.addAttribute("categories", categoryService.getCategories());
        return "task"; // Points to `task.html`
    }

    // Update an existing task
    @PostMapping("/{id}")
    public String updateTask(@PathVariable String id,
                             @ModelAttribute Task task) {
        notesService.updateTask(id, task);
        return "redirect:/task";
    }

    // Delete a task
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable String id) {
        notesService.deleteTask(id);
        return "redirect:/task";
    }
}
