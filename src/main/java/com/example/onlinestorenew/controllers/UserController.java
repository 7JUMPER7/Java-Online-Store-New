package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {
    @GetMapping(value = "/user")
    public String Index(HttpServletRequest request, Map<String, Object> model) {
        model.put("cartCount", CartService.getCount(request.getSession()));
        return "/user/user";
    }
}
