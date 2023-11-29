package com.example.servicediary.controller.noRest;

import com.example.servicediary.dao.StudentDao;
import com.example.servicediary.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentDao studentDao;
    @Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping("")
    public String studentIndex(Model model){
        model.addAttribute("students", studentDao.findAll());
        return "student/studentIndex";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable("id")int id, Model model){
        Student student = studentDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Такой студент не найден"));
        model.addAttribute("student", student);
        return "student/showOne";
    }

}
