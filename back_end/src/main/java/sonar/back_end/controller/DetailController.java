package sonar.back_end.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.dto.DiaryDetailDto;
import sonar.back_end.dto.LetterDetailDto;
import sonar.back_end.service.DetailService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor

public class DetailController {
    private final DetailService detailService;

    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<DetailResponse> getDetail(
            @PathVariable Integer year,
            @PathVariable Integer month,
            @PathVariable Integer day,
            @RequestParam("userId") Long userId) {
        LocalDate date = LocalDate.of(year, month, day);
        return ResponseEntity.ok(detailService.getDetail(userId, date));
    }

    @Getter
    public static class DetailResponse {
        private final List<DiaryDetailDto> diaries;
        private final List<LetterDetailDto> letters;

        public DetailResponse(List<DiaryDetailDto> diaries, List<LetterDetailDto> letters) {
            this.diaries = diaries;
            this.letters = letters;
        }
    }
}

