package sonar.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.dto.DiaryDTO;
import sonar.back_end.entity.DiaryEntity;
import sonar.back_end.entity.UserEntity;
import sonar.back_end.repository.DiaryRepository;
import sonar.back_end.repository.UserRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController  // @Controller 대신
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @PostMapping("/write")
    public ResponseEntity<DiaryEntity> wirteDiary(@RequestBody DiaryDTO request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        DiaryEntity diary = DiaryEntity.builder()
                .user(user)
                .diaryContent(request.getDiaryContent())
                .imageUrl(request.getImageUrl())
                .color(request.getColor())
                .hashtag(request.getHashtag())
                .diaryDate(request.getDiaryDate())
                .build();

        return ResponseEntity.ok(diaryRepository.save(diary));
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Integer diaryId) {
        System.out.println(diaryId);
        DiaryEntity diary = diaryRepository.findById(Long.valueOf(diaryId))
                .orElseThrow(() -> new RuntimeException("Diary not found"));
        diaryRepository.delete(diary);
        return ResponseEntity.noContent().build();
    }
}
