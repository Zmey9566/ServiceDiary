package com.example.servicediary.controller.noRest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controllers {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/forAdmin")
    public String index() {
        return "forAdmin/index";
    }

    @PreAuthorize("hasRole('ROLE_MENTOR')")
    @GetMapping("/forMentor")
    public String index2() {
        return "forMentor/index";
    }

}
