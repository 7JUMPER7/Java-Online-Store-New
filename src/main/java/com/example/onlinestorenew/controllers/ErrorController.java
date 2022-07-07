package com.example.onlinestorenew.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping(value = "/error/403")
    public String Error403() {
        return "/errors/403";
    }
}
