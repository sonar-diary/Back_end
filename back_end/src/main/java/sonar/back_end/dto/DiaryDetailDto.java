package sonar.back_end.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DiaryDetailDto {
    private final Long diaryId;
    private final String imageUrl;
    private final String diaryContent;
    private final String color;
    private final String hashtag;

    public DiaryDetailDto(Long diaryId, String imageUrl, LocalDate diaryDate,
                          String diaryContent, String color, String hashtag) {
        this.diaryId = diaryId;
        this.imageUrl = imageUrl;
        this.diaryContent = diaryContent;
        this.color = color;
        this.hashtag = hashtag;
    }
}
