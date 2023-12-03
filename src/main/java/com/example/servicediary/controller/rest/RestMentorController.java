package com.example.servicediary.controller.rest;

import com.example.servicediary.Service.MentorService;
import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.dto.rest.MentorReadRestDto;
import com.example.servicediary.dto.rest.MentorSaveRestDto;
import com.example.servicediary.entity.Mentor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/mentor")
public class RestMentorController {
    private final MentorDao mentorDao;
    private final MentorService mentorService;

    @GetMapping("/{id}")
    public MentorReadRestDto getMentor(@PathVariable int id) {
        final var mayBeMentor = mentorDao.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Ментр с id: " + id + " не найден"));
        return MentorReadRestDto.builder()
                .id(mayBeMentor.getId())
                .family(mayBeMentor.getFamily())
                .name(mayBeMentor.getName())
                .price(mayBeMentor.getPrice())
                .build();
    }

    @PostMapping("")
    public ResponseEntity<MentorSaveRestDto> addNewMentor(@RequestBody MentorSaveRestDto mentorSaveRestDtoRestDto) {

        final var newMentor = Mentor.builder()
                .family(mentorSaveRestDtoRestDto.getFamily())
                .name(mentorSaveRestDtoRestDto.getName())
                .price(mentorSaveRestDtoRestDto.getPrice())
                .build();
        log.info("Получили объект newMentor: " + mentorSaveRestDtoRestDto.getFamily() +
                " " + mentorSaveRestDtoRestDto.getName());
        mentorDao.save(newMentor);
        log.info("Сохранение объекта newMentor успешно завершено");
        return ResponseEntity.ok(mentorSaveRestDtoRestDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MentorReadRestDto> updateMentor(@PathVariable("id") int id,
                                                          @RequestBody MentorReadRestDto mentorReadRestDto) {
        mentorService.update(mentorReadRestDto, id);

        return ResponseEntity.ok(mentorReadRestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        mentorDao.deleteById(id);
        return ResponseEntity.ok("object successfully deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<MentorReadRestDto>> getall() {
        final var all = mentorService.getAll();
        return ResponseEntity.ok(all);
    }
}
