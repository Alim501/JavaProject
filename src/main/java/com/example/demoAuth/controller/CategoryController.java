package com.example.demoAuth.controller;

import com.example.demoAuth.entity.Category;
import com.example.demoAuth.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "categories";
    }

    @PostMapping("")
    public String createCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);

        categoryService.createCategory(category);
        return "redirect:/category";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable String id,
                                 @ModelAttribute Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/category";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
}
