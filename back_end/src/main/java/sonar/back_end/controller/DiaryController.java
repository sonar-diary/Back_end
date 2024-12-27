package sonar.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.entity.DiaryEntity;
import sonar.back_end.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController  // @Controller 대신
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryRepository diaryRepository;

    @PostMapping("/write")
    public ResponseEntity<DiaryEntity> wirteDiary(@RequestParam String diaryContent,
                                                  @RequestParam String imageUrl,
                                                  @RequestParam String color,
                                                  @RequestParam String hashtag,
                                                  @RequestParam LocalDate diaryDate
    ) {

        DiaryEntity diary = DiaryEntity.builder()
                .diaryContent(diaryContent)
                .imageUrl(imageUrl)
                .color(color)
                .hashtag(hashtag)
                .diaryDate(diaryDate)  // 직접 LocalDate 사용
                .build();
        DiaryEntity savedDiary = diaryRepository.save(diary);
        return ResponseEntity.ok(savedDiary);
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
