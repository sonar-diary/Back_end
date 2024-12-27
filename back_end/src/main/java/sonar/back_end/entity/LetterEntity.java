package sonar.back_end.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "letters")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LetterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String letterContent;

    @Column(nullable = false)
    private LocalDate letterDate;

    @Builder
    public LetterEntity(UserEntity user, String letterContent, LocalDate letterDate) {
        this.user = user;
        this.letterContent = letterContent;
        this.letterDate = letterDate;
    }
}
