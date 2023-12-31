package com.example.servicediary.controller.noRest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorStudentDao;
import com.example.servicediary.dao.StudentDao;
import com.example.servicediary.dto.noRest.MentorReadDto;
import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Role;
import com.example.servicediary.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/forAdmin/mentor")
public class AdminController {

    private final MentorService<MentorReadDto, MentorSaveDto> mentorDao;
    private final MentorStudentDao mentorStudentDao;
    private final StudentDao studentDao;

    @GetMapping()
    public String mentorIndex(Model model) {
        model.addAttribute("mentors", mentorDao.findAllOrdered());
        return "forAdmin/mentor/mentorIndex";
    }

    @GetMapping("/{id}")
    public String showMentor(@PathVariable("id") int id, Model model) {
        var mentor = mentorDao.findById(id).orElseThrow(() -> new RuntimeException("Такого ментра не существует"));
        model.addAttribute("mentor", mentor);
        return "forAdmin/mentor/showOne";
    }

    @GetMapping("/save")
    public String saveMentor(@ModelAttribute("mentor") @Validated Mentor mentor) {
        return "forAdmin/mentor/save";
    }

    @PostMapping()
    public String create(@ModelAttribute("mentor") @Validated MentorSaveDto mentorSaveDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "forAdmin/mentor/save";
        }
        mentorDao.save(mentorSaveDto);
        return "redirect:/forAdmin/mentor";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("mentor", mentorDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Такой ментор не найден")));
        return "/forAdmin/mentor/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("mentor") @Validated MentorReadDto mentorReadDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "forAdmin/mentor/edit";
        }
        mentorDao.update(mentorReadDto, id);
        return "redirect:/forAdmin/mentor";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") int id) {
        mentorDao.deleteById(id);
        return "redirect:/forAdmin/mentor";
    }

    @GetMapping("/{id}/showStudent")
    public String getStudents(Model model, @PathVariable("id") int id) {
        List<Student> students = mentorStudentDao.findByMentorId(id).stream().map(m -> m.getStudent())
                .collect(Collectors.toList());
        model.addAttribute("students", students);
        return "forAdmin/mentor/showMentorStudents";
    }

    @GetMapping("/{id}/showMentor")
    public String getMentor(Model model, @PathVariable("id") int id) {
        List<Mentor> mentors = mentorStudentDao.findByStudentId(id).stream().map(m -> m.getMentor())
                .collect(Collectors.toList());
        model.addAttribute("mentors", mentors);
        return "forAdmin/mentor/showStudentMentors";
    }

    @GetMapping("/students")
    public String studentIndex(Model model){
        model.addAttribute("students", studentDao.findAll());
        return "forAdmin/mentor/studentIndex";
    }
}
