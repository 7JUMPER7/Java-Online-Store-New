package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.services.CategoryService;
import com.example.onlinestorenew.services.GoodService;
import com.example.onlinestorenew.services.ImageService;
import com.example.onlinestorenew.utils.FileUtil;
import com.example.onlinestorenew.utils.Helpers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GoodController {
    @GetMapping(value = "/admin/good/create")
    public String CreateView(Map<String, Object> model) {
        CategoryService categoryService = new CategoryService();
        model.put("categories", categoryService.getAllCategories());
        return "/good/create";
    }
    @PostMapping(value = "/admin/good/create")
    public RedirectView Create(
            @RequestParam(name = "images") MultipartFile[] images,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") Double price,
            @RequestParam(name = "category") int categoryId,
            @RequestParam(name = "description") String description
    ) {
        GoodEntity good = new GoodEntity();
        if(images != null && images.length > 0) {
            good.setPreviewImage(Helpers.generateUUIDName(images[0].getOriginalFilename()));
        }
        good.setName(name);
        good.setPrice(price);
        good.setCategoryId(categoryId);
        good.setDescription(description);

        GoodService goodService = new GoodService();
        GoodEntity savedGood = goodService.createGood(good);
        if(savedGood != null) {
            ImageService imageService = new ImageService();
            imageService.createImages(images, savedGood.getId(), savedGood.getPreviewImage());
        }

        return new RedirectView("/");
    }

    @GetMapping(value = "/good/{id}")
    public String GoodView(Map<String, Object> model, @PathVariable(name = "id") Integer id) {
        System.out.println(id);
        if(id == null) {
            return "/errors/404";
        }

        GoodService goodService = new GoodService();
        GoodEntity good = goodService.findById(id);
        if(good != null) {
            ImageService imageService = new ImageService();
            List<ImageEntity> images = imageService.getGoodImages(good.getId());
            model.put("good", good);
            model.put("images", images);
            return "/good/good";
        }

        return "/errors/404";
    }

    @GetMapping(value = "/admin/good/delete/{id}")
    public String DeleteGoodConfirm(@PathVariable(name = "id") Integer id, Map<String, Object> model) {
        if(id == null) {
            return "/errors/404";
        }

        GoodService goodService = new GoodService();
        model.put("good", goodService.findById(id));
        return "/admin/deleteConfirm";
    }
    @PostMapping(value = "/admin/good/delete/{id}")
    public RedirectView DeleteGood(@PathVariable(name = "id") Integer id) {
        if(id == null) {
            return new RedirectView("/");
        }

        GoodService goodService = new GoodService();
        goodService.deleteGood(id);
        return new RedirectView("/");
    }
}
