package sonar.back_end.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DiaryDTO {
    private Long userId;
    private String diaryContent;
    private String imageUrl;
    private String color;
    private String hashtag;
    private LocalDate diaryDate;
}
