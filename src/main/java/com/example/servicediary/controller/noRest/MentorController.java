package com.example.servicediary.controller.noRest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.Service.noRest.MentorServiceImpl;
import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    private final MentorService<MentorReadDto, MentorSaveDto> mentorDao;

    @Autowired
    public MentorController(MentorService mentorDao) {
        this.mentorDao = mentorDao;
    }

    @GetMapping()
    public String mentorIndex(Model model) {
        model.addAttribute("mentors", mentorDao.findAllOrdered());
        return "mentor/mentorIndex";
    }

    @GetMapping("/{id}")
    public String showMentor(@PathVariable("id") int id, Model model) {
        var mentor = mentorDao.findById(id).orElseThrow(() -> new RuntimeException("Такого ментра не существует"));
        model.addAttribute("mentor", mentor);
        return "mentor/showOne";
    }

    @GetMapping("/save")
    public String saveMentor(@ModelAttribute("mentor") @Validated Mentor mentor) {
        return "mentor/save";
    }

    @PostMapping()
    public String create(@ModelAttribute("mentor") @Validated MentorSaveDto mentorSaveDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "mentor/save";
        }
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
    public String update(@PathVariable("id") int id, @ModelAttribute("mentor") @Validated MentorReadDto mentorReadDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/mentor/edit";
        }
        mentorDao.update(mentorReadDto, id);
        return "redirect:/mentor";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        mentorDao.deleteById(id);
        return "redirect:/mentor";
    }
}
