package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.GoodEntity;
import com.example.onlinestorenew.services.CartService;
import com.example.onlinestorenew.services.GoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/index"})
    public String Index(Map<String, Object> model, HttpServletRequest request) {
        GoodService goodService = new GoodService();
        List<GoodEntity> goods = goodService.findAllGoods();
        model.put("goodsList", goods);
        model.put("cartCount", CartService.getCount(request.getSession()));
        return "/index";
    }
}
