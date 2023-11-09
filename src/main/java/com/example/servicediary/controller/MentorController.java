package com.example.servicediary.controller;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dto.MentorReadDto;
import com.example.servicediary.dto.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    private final MentorService mentorDao;

    @Autowired
    public MentorController(MentorService mentorDao) {
        this.mentorDao = mentorDao;
    }

    @GetMapping()
    public String mentorIndex(Model model){
        model.addAttribute("mentors", mentorDao.getAllById());
        return "mentor/mentorIndex";
    }

    @GetMapping("/{id}")
    public String showMentor(@PathVariable("id")int id, Model model) {
        var mentor = mentorDao.findById(id).orElseThrow(() -> new RuntimeException("Такого ментра не существует"));
        model.addAttribute("mentor", mentor);
        return "mentor/showOne";
    }

    @GetMapping("/save")
    public String saveStudent(@ModelAttribute("mentor") Mentor mentor) {
        return "mentor/save";
    }

    @PostMapping()
    public String create(@ModelAttribute("mentor") MentorSaveDto mentorSaveDto) {
        mentorDao.save(mentorSaveDto);
        return "redirect:/mentor";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("mentor", mentorDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Такой ментор не найден")));
        return "mentor/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("mentor") MentorReadDto mentorReadDto, @PathVariable("id") int id) {
        mentorDao.update(mentorReadDto, id);
        return "redirect:/mentor";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        mentorDao.deleteById(id);
        return "redirect:/mentor";
    }
}
