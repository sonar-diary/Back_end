package sonar.back_end.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sonar.back_end.controller.CalendarController;
import sonar.back_end.dto.CalendarDiaryDto;
import sonar.back_end.dto.CalendarLetterDto;
import sonar.back_end.repository.DiaryRepository;
import sonar.back_end.repository.LetterRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final DiaryRepository diaryRepository;
    private final LetterRepository letterRepository;

    public CalendarController.CalendarResponse getCalendarData(Long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        List<CalendarDiaryDto> diaries = diaryRepository.findByUserIdAndDiaryDateBetween(
                        userId, startDate, endDate)
                .stream()
                .map(diary -> new CalendarDiaryDto(diary.getImageUrl(), diary.getDiaryDate()))
                .collect(Collectors.toList());

        List<CalendarLetterDto> letters = letterRepository.findByUserIdAndLetterDateBetween(
                        userId, startDate, endDate)
                .stream()
                .map(letter -> new CalendarLetterDto(letter.getId(), letter.getLetterDate()))
                .collect(Collectors.toList());

        return new CalendarController.CalendarResponse(diaries, letters);
    }
}
