package sonar.back_end.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class LetterDTO {
    private Long userId;
    private String letterContent;
    private LocalDate letterDate;
}
