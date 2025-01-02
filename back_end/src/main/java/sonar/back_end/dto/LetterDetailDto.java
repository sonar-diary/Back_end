package sonar.back_end.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LetterDetailDto {
    private final Long letterId;
    private final String letterContent;

    public LetterDetailDto(Long letterId, LocalDate letterDate, String letterContent) {
        this.letterId = letterId;
        this.letterContent = letterContent;
    }
}
