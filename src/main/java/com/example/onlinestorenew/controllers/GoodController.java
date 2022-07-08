package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.models.ImageEntity;
import com.example.onlinestorenew.services.GoodService;
import com.example.onlinestorenew.services.ImageService;
import com.example.onlinestorenew.utils.FileUtil;
import com.example.onlinestorenew.utils.Helpers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodController {
    @GetMapping(value = "/admin/good/create")
    public String CreateView() {
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
}
