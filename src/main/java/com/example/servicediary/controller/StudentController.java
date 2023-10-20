package com.example.servicediary.controller;

import com.example.servicediary.dao.StudentDaoImpl;
import com.example.servicediary.entity.Student;
import com.example.servicediary.util.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentDaoImpl studentDao = new StudentDaoImpl(Student.class, HibernateUtil.buildSessionFactory()
            .openSession());

    @GetMapping("")
    public String studentIndex(Model model){
        model.addAttribute("students", studentDao.getAll());
        return "/studentIndex";
    }

}
