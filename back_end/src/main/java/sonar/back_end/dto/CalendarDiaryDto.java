package sonar.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
public class CalendarDiaryDto {
    private final String imageUrl;
    private final LocalDate diaryDate;

    public CalendarDiaryDto(String imageUrl, LocalDate diaryDate) {
        this.imageUrl = imageUrl;
        this.diaryDate = diaryDate;
    }
}
