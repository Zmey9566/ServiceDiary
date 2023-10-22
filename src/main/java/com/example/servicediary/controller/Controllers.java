package com.example.servicediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {
    @GetMapping()
    public String index() {
        return "/index";
    }
}
