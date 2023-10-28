package com.example.servicediary.controller;

import com.example.servicediary.dao.MentorDaoImpl;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.util.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    private final MentorDaoImpl mentorDao = new MentorDaoImpl(Mentor.class, HibernateUtil.buildSessionFactory()
            .openSession());

    @GetMapping()
    public String mentorIndex(Model model){
        model.addAttribute("mentors", mentorDao.getAll());
        return "mentor/mentorIndex";
    }

    @GetMapping("/{id}")
    public String showMentor(@PathVariable("id")int id, Model model) {
        Mentor mentor = mentorDao.findById(id);
        model.addAttribute("mentor", mentor);
        return "mentor/showOne";
    }

    @GetMapping("/save")
    public String saveStudent(@ModelAttribute("mentor") Mentor mentor) {
        return "mentor/save";
    }

    @PostMapping()
    public String create(@ModelAttribute("mentor") Mentor mentor) {
        mentorDao.save(mentor);
        return "redirect:/mentor";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("mentor", mentorDao.findById(id));
        return "mentor/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("mentor") Mentor mentor) {
        mentorDao.update(mentor);
        return "redirect:/mentor";
    }

//    @GetMapping("/{id}/delete")
//    public String delete(Model model, @PathVariable("id") int id) {
//        model.addAttribute("mentor", mentorDao.findById(id));
//        return "mentor/delete";
//    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        mentorDao.removeById(id);
        return "redirect:/mentor";
    }
}
