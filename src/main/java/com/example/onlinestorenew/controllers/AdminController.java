package com.example.onlinestorenew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping(value = "/admin/test")
    public String Test() {
        return "/admin/test";
    }
}
