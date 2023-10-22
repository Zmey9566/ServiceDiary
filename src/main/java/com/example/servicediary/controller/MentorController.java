package com.example.servicediary.controller;

import com.example.servicediary.dao.MentorDaoImpl;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.util.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    private final MentorDaoImpl mentorDao = new MentorDaoImpl(Mentor.class, HibernateUtil.buildSessionFactory()
            .openSession());

    @GetMapping("")
    public String mentorIndex(Model model){
        model.addAttribute("mentors", mentorDao.getAll());
        return "mentor/mentorIndex";
    }

    @GetMapping("/{id}")
    public String showMentor(@PathVariable("id")int id, Model model){
        Mentor mentor = mentorDao.findById(id);
        model.addAttribute("mentor", mentor);
        return "mentor/showOne";
    }

    @GetMapping("/save")
    public String saveStudent() {
        return "mentor/save";
    }
}
