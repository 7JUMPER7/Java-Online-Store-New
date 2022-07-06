package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/index"})
    public String Index(Map<String, Object> model) {
        GoodService goodService = new GoodService();
        List<GoodEntity> goods = goodService.findAllGoods();
        System.out.println(goods);
        model.put("goodsList", goods);
        return "/index";
    }
}
