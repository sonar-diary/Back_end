package sonar.back_end.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonar.back_end.dto.CalendarDiaryDto;
import sonar.back_end.dto.CalendarLetterDto;
import sonar.back_end.service.CalenderService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalenderService calendarService;


    @GetMapping("/{year}/{month}")
    public ResponseEntity<CalendarResponse> getCalendarData(@PathVariable Integer year,
                              @PathVariable Integer month,
                              @RequestParam("userId") Long userId) {

        return ResponseEntity.ok(calendarService.getCalendarData(userId, year, month));
    }

    @Getter
    public static class CalendarResponse {
        private final List<CalendarDiaryDto> diaries;
        private final List<CalendarLetterDto> letters;

        public CalendarResponse(List<CalendarDiaryDto> diaries, List<CalendarLetterDto> letters) {
            this.diaries = diaries;
            this.letters = letters;
        }
    }


}
