package sonar.back_end.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sonar.back_end.controller.DetailController;
import sonar.back_end.dto.DiaryDetailDto;
import sonar.back_end.dto.LetterDetailDto;
import sonar.back_end.repository.DiaryRepository;
import sonar.back_end.repository.LetterRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DiaryRepository diaryRepository;
    private final LetterRepository letterRepository;

    public DetailController.DetailResponse getDetail(Long userId, LocalDate date) {
        List<DiaryDetailDto> diaries = diaryRepository.findByUserIdAndDiaryDate(userId, date)
                .stream()
                .map(diary -> new DiaryDetailDto(
                        diary.getId(),
                        diary.getImageUrl(),
                        diary.getDiaryDate(),
                        diary.getDiaryContent(),
                        diary.getColor(),
                        diary.getHashtag()))
                .collect(Collectors.toList());

        List<LetterDetailDto> letters = letterRepository.findByUserIdAndLetterDate(userId, date)
                .stream()
                .map(letter -> new LetterDetailDto(
                        letter.getId(),
                        letter.getLetterDate(),
                        letter.getLetterContent()))
                .collect(Collectors.toList());

        return new DetailController.DetailResponse(diaries, letters);
    }
}
