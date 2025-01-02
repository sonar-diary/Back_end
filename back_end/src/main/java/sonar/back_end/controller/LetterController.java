package sonar.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.dto.LetterDTO;
import sonar.back_end.entity.DiaryEntity;
import sonar.back_end.entity.LetterEntity;
import sonar.back_end.entity.UserEntity;
import sonar.back_end.repository.LetterRepository;
import sonar.back_end.repository.UserRepository;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/letter")
public class LetterController {

    private final LetterRepository letterRepository;
    private final UserRepository userRepository;

    @PostMapping("/write")
    public ResponseEntity<LetterEntity> wirteLetter(@RequestBody LetterDTO request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LetterEntity letter = LetterEntity.builder()
                .user(user)
                .letterContent(request.getLetterContent())
                .letterDate(request.getLetterDate())  // 직접 LocalDate 사용
                .build();

        return ResponseEntity.ok(letterRepository.save(letter));
    }

    @DeleteMapping("/{letterId}")
    public ResponseEntity<Void> letterDiary(@PathVariable Integer letterId) {
        LetterEntity letter = letterRepository.findById(Long.valueOf(letterId))
                .orElseThrow(() -> new RuntimeException("Letter not found"));
        letterRepository.delete(letter);
        return ResponseEntity.noContent().build();
    }
}
