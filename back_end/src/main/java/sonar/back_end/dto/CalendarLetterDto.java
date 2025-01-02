package sonar.back_end.dto;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class CalendarLetterDto {
    private final Long letterId;
    private final LocalDate letterDate;

    public CalendarLetterDto(Long letterId, LocalDate letterDate) {
        this.letterId = letterId;
        this.letterDate = letterDate;
    }
}
