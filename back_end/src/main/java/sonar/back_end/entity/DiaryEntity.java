package sonar.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

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
    private User user;

    @Column(nullable = false, columnDefinition = "Text")
    private String content;


    private String imageUrl;

    @Column(nullable = false)
    private LocalDate diaryDate;

    @Builder
    public DiaryEntity(User user, String content, String imageUrl, LocalDate diaryDate) {
        this.user = user;
        this.content = content;
        this.imageUrl = imageUrl;
        this.diaryDate = diaryDate;
    }
}
