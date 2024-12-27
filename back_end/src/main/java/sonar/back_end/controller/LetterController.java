package sonar.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.entity.DiaryEntity;
import sonar.back_end.entity.LetterEntity;
import sonar.back_end.repository.LetterRepository;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/letter")
public class LetterController {

    private final LetterRepository letterRepository;

    @PostMapping("/wirte")
    public ResponseEntity<LetterEntity> wirteLetter(@RequestParam String letterContent,
                                                    @RequestParam LocalDate letterDate) {
        LetterEntity letter = LetterEntity.builder()
                .letterContent(letterContent)
                .letterDate(letterDate)  // 직접 LocalDate 사용
                .build();
        LetterEntity savedLetter = letterRepository.save(letter);
        return ResponseEntity.ok(savedLetter);
    }

    @DeleteMapping("/{letterId}")
    public ResponseEntity<Void> letterDiary(@PathVariable Integer letterId) {
        LetterEntity letter = letterRepository.findById(Long.valueOf(letterId))
                .orElseThrow(() -> new RuntimeException("Letter not found"));
        letterRepository.delete(letter);
        return ResponseEntity.noContent().build();
    }
}
