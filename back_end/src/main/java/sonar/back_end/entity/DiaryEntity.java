package sonar.back_end.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "diaries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, columnDefinition = "Text")
    private String diaryContent;

    private String imageUrl;

    private String color;

    private String hashtag;

    @Column(nullable = false)
    private LocalDate diaryDate;

    @Builder
    public DiaryEntity(UserEntity user, String diaryContent, String imageUrl, LocalDate diaryDate, String color, String hashtag) {
        this.user = user;
        this.diaryContent = diaryContent;
        this.imageUrl = imageUrl;
        this.diaryDate = diaryDate;
        this.color = color;
        this.hashtag = hashtag;
    }
}
