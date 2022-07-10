package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.dao.CategoryDao;
import com.example.onlinestorenew.models.CategoryEntity;
import com.example.onlinestorenew.services.CategoryService;
import com.example.onlinestorenew.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class CategoryController {
    @GetMapping(value = "/admin/category/create")
    public String CreateView(Map<String, Object> model) {
        return "/category/create";
    }
    @PostMapping(value = "/admin/category/create")
    public RedirectView CreateCategory(@RequestParam(value = "name") String name) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        CategoryService categoryService = new CategoryService();
        categoryService.createCaregory(category);
        return new RedirectView("/");
    }

    @GetMapping(value = "/admin/category")
    public String AllCategories(Map<String, Object> model) {
        CategoryService categoryService = new CategoryService();
        model.put("categories", categoryService.getAllCategories());
        return "/category/index";
    }

    @GetMapping(value = "/admin/category/delete/{id}")
    public String DeleteCategoryConfirm(@PathVariable(name = "id") Integer id, Map<String, Object> model) {
        if(id == null) {
            return "/errors/404";
        }

        CategoryService categoryService = new CategoryService();
        CategoryEntity category = categoryService.getById(id);
        model.put("controller", "category");
        model.put("name", category.getName());
        model.put("id", category.getId());
        return "/admin/deleteConfirm";
    }
    @PostMapping(value = "/admin/category/delete/{id}")
    public RedirectView DeleteCategory(@PathVariable(name = "id") Integer id) {
        if(id == null) {
            return new RedirectView("/");
        }

        CategoryService categoryService = new CategoryService();
        categoryService.deleteCategory(id);
        return new RedirectView("/");
    }

    @GetMapping(value = "/admin/category/edit/{id}")
    public String EditCategoryView(@PathVariable(name = "id") Integer id, Map<String, Object> model) {
        CategoryService categoryService = new CategoryService();
        model.put("category", categoryService.getById(id));
        return "/category/edit";
    }
    @PostMapping(value = "/admin/category/edit/{id}")
    public RedirectView EditCategory(@PathVariable(name = "id") Integer id, @RequestParam(name = "name") String name) {
        CategoryService categoryService = new CategoryService();
        CategoryEntity category = categoryService.getById(id);
        category.setName(name);
        categoryService.updateCategory(category);
        return new RedirectView("/");
    }
}
